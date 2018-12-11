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
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.model.pages.PageContentURL;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.TenderOld;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.PageServiceProzorro;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.TenderDataServiceProzorro;
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
	int allTenders;
	private Date dateFrom;
	private Date dateTill;
	private String approximatelyTime;
	private Boolean urlConnection = false;
	private ProzorroApp prozorroApp;

	private PageServiceProzorro pageServiceProzorro;
	private TenderDataServiceProzorro tenderDataServiceProzorro;

	private PropertyFields propertyFields;
	private List<PageContent> pageContentList = null;
	private boolean findData = false;


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

	public void onCheckDataForPeriod(ActionEvent actionEvent) {
		String checkDate = DateUtils.checkDatesForPeriod(datePickerFrom.getValue(), datePickerTill.getValue());
		findData = false;
		if (checkDate == null) {
			//buttonGetData.setDisable(true);
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

			checkDataForPeriodMessage();

			Task task = new Task() {
				@Override
				protected Integer call() throws Exception {
					//System.out.println(buttonGetPages.getScene().getClass().toString());

					setWaitingProcess(true);
					try {
						ParsingResultData resultData = pageServiceProzorro.getApproximatelyParsingTimeForPeriod(dateFrom, dateTill);
						findData = resultData.isHasData();
						//System.out.println("Total time: " + totalTime + "sec, " + (totalTime/60)+ "m");


						String mess = "Найдено страниц с " + comboBoxDataType.getValue() + ": " + resultData.getListSize() +
								              ". Приблизительное время обработки: " + DateUtils.getTextTime(resultData.getParsingTime() / 1000000);
						logger.info(mess);
						textArea.appendText(mess + "\n");

					} catch (IOException e) {
						//e.printStackTrace();
						textArea.appendText(e.getMessage() + "\n");
						Dialogs.showErrorDialog(e, "Exception Dialog", "Look, an Exception Dialog", e.getMessage());
					}

					setWaitingProcess(false);
					return pageContentList.size();
				}
			};
			Thread th = new Thread(task);
			//th.setDaemon(true);
			th.start();

		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Даты установлены не верно!", checkDate);
			datePickerFrom.requestFocus();
		}
	}

	private void setWaitingProcess(boolean isWaiting) {
		prozorroApp.getRoot().setDisable(isWaiting);

		buttonGetPages.getParent().getParent().setDisable(isWaiting);
		buttonGetPages.getParent().getScene().setCursor((isWaiting) ? Cursor.WAIT : Cursor.DEFAULT); //Change cursor to wait style

		buttonGetData.setDisable(findData);
	}

	private void checkDataForPeriodMessage() {

		String textDateFrom = DateUtils.parseDateToString(dateFrom, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
		String textDateTill = DateUtils.parseDateToString(dateTill, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));

		textArea.appendText("Отбираются записи по '" + comboBoxDataType.getValue() + "' за период с " + textDateFrom + " по " + textDateTill + "\n");
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

				/*Thread th = new Thread(getTendersTask(pageContentList));

				th.start();*/

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
		this.propertyFields = new PropertyFields(prozorroApp.getProperties());
		this.pageServiceProzorro = new PageServiceProzorro(propertyFields);
		this.tenderDataServiceProzorro = new TenderDataServiceProzorro(propertyFields.getPropertiesStringValue(AppProperty.START_PAGE) + File.separator);
	}

}
