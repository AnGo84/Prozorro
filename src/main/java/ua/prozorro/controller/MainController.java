package ua.prozorro.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.Prozorro;
import ua.prozorro.ProzorroApp;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.model.DataType;
import ua.prozorro.model.pages.PageContentURL;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.TenderOld;
import ua.prozorro.service.PageElementService;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MainController {
	private static final Logger logger = LogManager.getRootLogger();

	private Date dateFrom;
	private Date dateTill;
	private String approximatelyTime;

	private Boolean urlConnection = false;


	private ProzorroApp prozorroApp;

	int allTenders;

	private List<PageContentURL> pageContentList = null;

	@FXML
	private Button buttonGetPages;
	@FXML
	private Button buttonGetData;

	@FXML
	private TextArea textArea;

	@FXML
	private ComboBox<DataType> comboBoxDataType;

	@FXML
	private DatePicker datePickerFrom;
	@FXML
	private DatePicker datePickerTill;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private MenuItem menuItemParseList;
	@FXML
	private MenuItem menuItemParseTender;

	@FXML
	private void initialize() {
		buttonGetPages.setTooltip(new Tooltip("Выбрать страницы за указанный период"));
		buttonGetData.setTooltip(new Tooltip("Выбрать тендеры с отобранных страниц"));

		textArea.appendText("Start" + "\n");

		datePickerFrom.setValue(LocalDate.now());
		datePickerTill.setValue(LocalDate.now());

		comboBoxDataType.getItems().setAll(DataType.values());
		comboBoxDataType.setValue(DataType.TENDERS);
		/*
		try {

			Prozorro.parseUrl(new URL(Prozorro.START_PAGE));
			urlConnection = true;

		} catch (IOException e) {
			textArea.appendText(e.getMessage() + "\n");
			Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка подключения по URL", e.getMessage());
		}
		*/
	}

	public void onCheckData(ActionEvent actionEvent) {
		String checkDate = DateUtils.checkDatesForPeriod(datePickerFrom.getValue(),datePickerTill.getValue());

		if ( checkDate==null)
		{
			buttonGetData.setDisable(true);
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			textArea.appendText("Отбираются записи за период с " + Prozorro.simpleDateShotFormat.format(dateFrom) + " по " + Prozorro.simpleDateShotFormat.format(dateTill) + "\n");


			Task task = new Task() {
				@Override
				protected Integer call() throws Exception {
//                    System.out.println(buttonGetPages.getScene().getClass().toString());

					prozorroApp.getRoot().setDisable(true);

					buttonGetPages.getParent().getParent().setDisable(true);
					buttonGetPages.getParent().getScene().setCursor(Cursor.WAIT); //Change cursor to wait style
					try {
						pageContentList = Prozorro.getPagesList(dateFrom, dateTill);

						approximatelyTime = Prozorro.getTextTime(Prozorro.getAvgParsingSize(pageContentList.get(0), dateTill) * pageContentList.size());

						textArea.appendText("Найдено страниц с тендерами: " + pageContentList.size() + "\n");

						if (pageContentList != null && !pageContentList.isEmpty() && prozorroApp.getSession() != null) {
							buttonGetData.setDisable(false);
						}
					} catch (ParseException | IOException e) {
						textArea.appendText(e.getMessage() + "\n");
						Dialogs.showErrorDialog(e, "Exception Dialog", "Look, an Exception Dialog", e.getMessage());
					}
					buttonGetPages.getParent().getScene().setCursor(Cursor.DEFAULT); //Change cursor to default style
					buttonGetPages.getParent().getParent().setDisable(false);

					prozorroApp.getRoot().setDisable(false);
					return pageContentList.size();
				}
			};
			Thread th = new Thread(task);
//            th.setDaemon(true);
			th.start();

		}
		else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Даты установлены не верно!", checkDate);
			datePickerFrom.requestFocus();
		}
		/*else {
			textArea.appendText("Нет подключения к БД. Зачем работать?");
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет подключения к БД!", "Нет подключения к БД. Зачем работать!");
		}*/

	}

	public void onGetData(ActionEvent actionEvent) {
		if (pageContentList.size() > 0) {
			if (Dialogs.showConfirmDialog("Подтвердите действие", "Время выполнения примерно " + approximatelyTime, "Время выполнения примерно " + approximatelyTime + ". Продолжить?")) {

				/*if (prozorroApp.getConnection() != null) {
					try {
						SQLConnection.cleanTables(prozorroApp.getConnection());
						textArea.appendText("Таблицы очищены" + "\n");
					} catch (SQLException e) {
						textArea.appendText(e.getMessage() + "\n");
					}
				}*/

				Thread th = new Thread(getTendersTask(pageContentList));
//            th.setDaemon(true);
				th.start();

			}
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет страниц для разбора!", "За указанный период нет данных для разбора.");
		}
	}

	private Task getTendersTask(List<PageContentURL> pageContents) {
		progressBar.setProgress(0);
		progressIndicator.setProgress(0);
		Task task = new Task() {
			@Override
			protected Integer call() throws Exception {
//                    System.out.println(buttonGetPages.getScene().getClass().toString());
				int pageCount = 0;
				int findTenders = 0;
				buttonGetData.getParent().getParent().setDisable(true);
				buttonGetData.getParent().getScene().setCursor(Cursor.WAIT); //Change cursor to wait style

				Session sessionObj = null;
				try {
					sessionObj = prozorroApp.getSession().getSessionFactory().openSession();


					//-------------------------------------------
					for (PageContentURL pageContent : pageContents) {
						List<TenderOld> tenderList = null;
						try {
							tenderList = Prozorro.getTendersFromPage(pageContent, dateTill);
						} catch (IOException e) {
							textArea.appendText(e.getMessage() + "\n");
						} catch (ParseException e) {
							textArea.appendText(e.getMessage() + "\n");
						}
						pageCount++;
						findTenders += tenderList.size();
					/*if (prozorroApp.getConnection() != null) {
						try {
							SQLConnection.insertTenders(prozorroApp.getConnection(), tenderList);
//                            System.out.println("Added " + tenderList.size() + " new Tenders to DB");
							textArea.appendText("Добавлено " + tenderList.size() + " новых тендеров в базу. Страница: " + pageContent.getPageHeader() + "\n");
						} catch (SQLException e) {
							textArea.appendText(e.getMessage() + "\n");
						}
					}*/
						PageElementService pageElementService = new PageElementService(sessionObj);
						pageElementService.savePageElementList(pageContent.getPageElementList());


						progressBar.setProgress(Double.valueOf(pageCount) / pageContents.size());
						progressIndicator.setProgress(Double.valueOf(pageCount) / pageContents.size());
					}
					//----------------------------------


				} catch (Exception sqlException) {

					if (null != sessionObj.getTransaction()) {

						System.out.println("\n.......Transaction Is Being Rolled Back.......");
						textArea.appendText("SQL exception: " + sqlException.getMessage() + "\n");
						textArea.appendText(".......Transaction Is Being Rolled Back......." + "\n");

						sessionObj.getTransaction().rollback();

					}
					sqlException.printStackTrace();
				} finally {
					if (sessionObj != null) {
						sessionObj.close();
					}
				}


				buttonGetData.getParent().getScene().setCursor(Cursor.DEFAULT); //Change cursor to default style
				buttonGetData.getParent().getParent().setDisable(false);
				allTenders = findTenders;
				textArea.appendText("Найдено " + allTenders + " тендеров " + "\n");
				return findTenders;
			}
		};
		return task;
	}



	public void onParsePage(ActionEvent actionEvent) {
		if (urlConnection) {
			String inputText = Dialogs.showInputDialog("Разобрать страницу по URl", "Укажите страницу для разбора", "Страница:");
			textArea.appendText("Страница для разбора: " + inputText + "\n");
			if (!inputText.isEmpty() && inputText != null) {

				buttonGetData.getParent().getScene().setCursor(Cursor.WAIT);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							PageContentURL pageContent = Prozorro.getPageContent(new URL(inputText));
							for (PageElement pageElement : pageContent.getPageElementList()) {
								textArea.appendText(pageElement.toString() + "\n");
							}
						} catch (org.json.simple.parser.ParseException e) {
							textArea.appendText(e.getMessage() + "\n");
							Dialogs.showErrorDialog(e, "Exception Dialog", "Разбора страницы по URL", e.getMessage());

						} catch (IOException e) {
							textArea.appendText(e.getMessage() + "\n");
							Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка подключения по URL", e.getMessage());
						}
						buttonGetData.getParent().getScene().setCursor(Cursor.DEFAULT);
					}
				});

			}

		} else
			Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка ", "Ошибка подключенияпо ссылке",
								"Ошибка доступа или ошибка данных с сайта Прозоро");
	}

	public void onClean(ActionEvent actionEvent) {
		if (Dialogs.showConfirmDialog("Очистить логи", "Вы собираетесь очистить текст с логими",
									  "Весь текст будет утерян. Продолжить?")) {
			textArea.clear();
		}
	}

	public void onClose(ActionEvent actionEvent) {
		if (prozorroApp.isConfirmShutDown()) {
			Platform.exit();
			System.exit(0);
		}
	}

	public void onParseTender(ActionEvent actionEvent) {
		if (urlConnection) {
			String inputText = Dialogs.showInputDialog("Разобрать тендер по ID", "Укажите ID тендера для разбора", "Тендер:");
			textArea.appendText("Тендер для разбора: " + inputText + "\n");
			if (!inputText.isEmpty() && inputText != null) {
				buttonGetData.getParent().getScene().setCursor(Cursor.WAIT);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							TenderOld tender = Prozorro.getTender(inputText);
							textArea.appendText(tender.toString() + "\n");
						} catch (IOException e) {
							textArea.appendText(e.getMessage() + "\n");
							Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка доступа по ID", e.getMessage());
						}
						buttonGetData.getParent().getScene().setCursor(Cursor.DEFAULT);
					}
				});
			}

//            try {
//                TenderOld tender = getTender(inputText);
//                textArea.appendText(tender.toString() + "\n");
//            } catch (IOException e) {
//                textArea.appendText(e.getMessage() + "\n");
//                showErrorDialog(e, "Exception Dialog", "Ошибка доступа по ID", e.getMessage());
//            }
		} else
			Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка ", "Ошибка подключенияпо ссылке", "Ошибка доступа или ошибка данных с сайта Прозоро");

	}

	public void onSaveLogToFile(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(buttonGetPages.getScene().getWindow());

		if (file != null) {
			textArea.appendText("Выбран  файл для сохранения журнала: " + file.getAbsoluteFile() + "\n");
			try {
				FileUtils.SaveToFile(textArea.getText(), file);
			} catch (IOException e) {
				Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка сохранения", "Ошибка при попытке сохранить файл " + file.getAbsoluteFile(), e.getMessage());
			}
		}
	}

	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		if (prozorroApp.getSession() == null) {
			textArea.appendText("Подключено к БД" + "\n");
		} else {
			textArea.appendText("БД не доступна" + "\n");
		}
		/*if (prozorroApp.getConnection()!=null){
			try {
				textArea.appendText("Подключено к БД " + prozorroApp.getConnection().getSchema() + "\n");
			} catch (SQLException e) {
				textArea.appendText("Подключено к БД " + "\n");
				e.printStackTrace();
			}
		}else {
			textArea.appendText("БД не доступна" + "\n");
		}
		if (prozorroApp.getProperties()!=null){
			//textArea.appendText("Открыт файл настроек: " + PropertiesUtils.toString(prozorroApp.getProperties()) + "\n");
		}else {
			textArea.appendText("Ошибка чтения файла настроек" + "\n");
		}*/

	}

}
