package ua.prozorro.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.Prozorro;
import ua.prozorro.ProzorroApp;
import ua.prozorro.entity.TenderDTOUtils;
import ua.prozorro.entity.pages.TenderPageDTO;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.PageServiceProzorro;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.TenderDataServiceProzorro;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.prozorro.model.pages.PageContentURL;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.model.tenders.TenderOld;
import ua.prozorro.service.PageService;
import ua.prozorro.service.ProzorroServiceFactory;
import ua.prozorro.service.TenderService;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.sql.HibernateFactory;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainController {
	private static final Logger logger = LogManager.getRootLogger();

	private static final int LOGGIN_TEXT_LIMIT=10000;

	private Date dateFrom;
	private Date dateTill;

	private Boolean urlConnection = false;
	private ProzorroApp prozorroApp;

	private PropertyFields propertyFields;

	private ParsingResultData resultData;


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

		ininListeners();

		textArea.appendText("Start" + "\n");

		//textArea.appendText("TEXT max length: " + String.valueOf(textArea.getMaxHeight()) + "\n");

		datePickerFrom.setValue(LocalDate.now());
		datePickerTill.setValue(LocalDate.now());

		comboBoxDataType.getItems().setAll(DataType.values());
		comboBoxDataType.setValue(DataType.TENDERS);

	}

	private void ininListeners() {
		// auto cleaning textAria
		textArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
			                    String oldValue, String newValue) {

				if (newValue != null && newValue.length() > LOGGIN_TEXT_LIMIT) {
					newValue = newValue.substring(newValue.length() - (LOGGIN_TEXT_LIMIT+1));
				}
				textArea.setText(newValue);
				/*String[] lines = newValue.split("\n", -1);
				if(lines.length>50){
					StringBuffer stringBuffer = new StringBuffer();
					for (int i=0;i <=50;i++){
						stringBuffer.append(lines[lines.length-50 + i]);
					}
					textArea.setText(stringBuffer.toString());
				}else{
					textArea.setText(newValue);
				}*/
			}
		});

		comboBoxDataType.valueProperty().addListener(new ChangeListener<DataType>() {
			@Override
			public void changed(ObservableValue ov, DataType t, DataType t1) {
				if (t != null && t1 != null && !t.equals(t1)) {
					resultData = new ParsingResultData();
					buttonGetData.setDisable(true);
				}

			}
		});
	}

	public void onCheckDataForPeriod(ActionEvent actionEvent) {
		String checkDate = DateUtils.checkDatesForPeriod(datePickerFrom.getValue(), datePickerTill.getValue());

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
						//https://stackoverflow.com/questions/1970239/in-java-how-do-i-get-the-difference-in-seconds-between-2-dates
						resultData = ProzorroServiceFactory.getApproximatelyParsingTimeForPeriod(propertyFields, comboBoxDataType.getValue(), dateFrom, dateTill);
						//System.out.println("Total time: " + totalTime + "sec, " + (totalTime/60)+ "m");

						String mess =
								"Найдено страниц с " + comboBoxDataType.getValue() + ": " + resultData.getListSize() +
										". Приблизительное время обработки: " +
										DateUtils.getTextTime(resultData.getParsingTime() / 1000000);
						logger.info(mess);
						textArea.appendText(mess + "\n");

					} catch (IOException e) {
						//e.printStackTrace();
						resultData = new ParsingResultData();
						textArea.appendText(e.getMessage() + "\n");
						Dialogs.showErrorDialog(e, "Exception Dialog", "Look, an Exception Dialog", e.getMessage());
					}

					setWaitingProcess(false);
					return resultData.getListSize();
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
		buttonGetPages.getParent().getScene()
				.setCursor((isWaiting) ? Cursor.WAIT : Cursor.DEFAULT); //Change cursor to wait style

		buttonGetData.setDisable(isWaiting || !resultData.isHasData());
	}

	private void checkDataForPeriodMessage() {

		String textDateFrom = DateUtils
				                      .parseDateToString(dateFrom, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
		String textDateTill = DateUtils
				                      .parseDateToString(dateTill, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));

		textArea.appendText(
				"Отбираются записи по '" + comboBoxDataType.getValue() + "' за период с " + textDateFrom + " по " +
						textDateTill + "\n");
	}

	public void onGetData(ActionEvent actionEvent) {
		progressBar.setProgress(0);
		progressIndicator.setProgress(0);
		if (resultData.isHasData()) {
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			if (Dialogs.showConfirmDialog("Подтвердите действие", "Примерное время выполнения " + DateUtils.getTextTime(resultData.getParsingTime() / 1000000),
					"Время отбора за период с " + DateUtils.dateToString(dateFrom) + "\n по " + DateUtils.dateToString(dateTill) + " " + DateUtils.getTextTime(resultData.getParsingTime() / 1000000) + ". Продолжить?")) {
				SessionFactory sessionFactory = null;
				try {
					sessionFactory = getSessionFactoryByDBName(PropertiesUtils.getPropertyString(prozorroApp.getProperties(), "db.type"));
					printConnectionResult(sessionFactory);
					Task task =getDataAndSave(sessionFactory, resultData);

					progressBar.progressProperty().bind(task.progressProperty());
					progressIndicator.progressProperty().bind(task.progressProperty());

					//textArea.textProperty().bindBidirectional(task.);

					Thread th = new Thread(task);

					th.start();
					//th.join();
				} catch (Exception e) {
					textArea.appendText("Exception: " + e.getMessage() + "\n");
					Dialogs.showErrorDialog(e, new DialogText("Ошибка отбора", "Ошибка при проверке данных за период",
							"При проверке данных по " + comboBoxDataType.getValue() + " за период c " +
									DateUtils.dateToString(dateFrom) + " по " + DateUtils.dateToString(dateTill) + " возникла ошибка: " + e.getMessage()), logger);
					e.printStackTrace();
				}
				/*finally {
					if (sessionFactory.isOpen()) {
						sessionFactory.close();
					}
				}*/
			}
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет данных для разбора!",
					"За указанный период нет данных по " + comboBoxDataType.getValue() + " для разбора.");
		}
	}

	private Task getDataAndSave(SessionFactory sessionFactory, ParsingResultData resultData) {
		//https://docs.oracle.com/javase/8/javafx/interoperability-tutorial/concurrency.htm
		//https://stackoverflow.com/questions/29963542/javafx-show-loading-dialog-for-longer-operations

		/*progressBar.setProgress(0);
		progressIndicator.setProgress(0);*/
		Task task = new Task() {
			@Override
			protected Integer call() throws Exception {

				setWaitingProcess(true);
				/*
				buttonGetData.getParent().getParent().setDisable(true);
				buttonGetData.getParent().getScene().setCursor(Cursor.WAIT); //Change cursor to wait style*/

				logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " +
						            DateUtils.dateToString(dateTill));

				PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);

				String currentPageURL = pageServiceProzorro.getTenderPageURL(dateFrom);
				logger.info("Start parsing from URL " + currentPageURL);
				textArea.appendText("Start parsing from URL " + currentPageURL + "\n");

				TenderPageDTO page = null;

				Session session = null;
				Transaction transaction = null;

				String text = "";

				ProzorroPageContent pageContent = null;

				int pageCount = 0;
				int pageElementCount = 0;
				try {
					dateTill = pageServiceProzorro.getDateTill(dateTill);

					pageContent = pageServiceProzorro.getPageContentFromURL(currentPageURL);

					Date nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
					//logger.info("Get first ProzorroPage: " + pageContent);
					logger.info("Get first ProzorroPage");

					session = sessionFactory.openSession();
					while (dateTill.compareTo(nextOffsetDate) >= 0 && pageContent.getPageElementList() != null &&
							       !pageContent.getPageElementList().isEmpty()) {

						if (isCancelled()) {
							updateMessage("Cancelled");
							break;
						}

						pageCount++;

						pageElementCount = 0;
						logger.info("Start parsing page №" + pageCount + ": ");

						for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
							pageElementCount++;

							PageService pageService = new PageService(session);

							transaction = session.beginTransaction();

							page = TenderDTOUtils.getPageDTO(pageElement);

							boolean updatedPage = pageService.savePage(page, session);
							if (updatedPage) {
								TenderService tenderService = new TenderService(session);

								TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(
										propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");

								TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
								text = tenderData.toString();
								TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
								tenderService.saveTender(tenderDTO, session);
							}
							logger.info(
									"ProzorroPage № " + pageCount + ", " + comboBoxDataType.getValue() + " on page № " + pageElementCount + ", added/updated: " +
											updatedPage);
							textArea.appendText("ProzorroPage № " + pageCount + ", " + comboBoxDataType.getValue() + " on page № " + pageElementCount + " id: " + page.getId() + ", added/updated: " + updatedPage + " \n");

							updateMessage("Mes: " + "ProzorroPage № " + pageCount + ", " + comboBoxDataType.getValue() + " on page № " + pageElementCount + " id: " + page.getId() + ", added/updated: " + updatedPage + " \n");
							session.flush();
							session.clear();
							transaction.commit();

						}

						updateProgress(pageCount, resultData.getListSize());

						/*progressBar.setProgress(Double.valueOf(pageCount) / resultData.getListSize());
						progressIndicator.setProgress(Double.valueOf(pageCount) / resultData.getListSize());*/

						logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
						pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
						nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());

					}

				} catch (Exception e) {
					//catch (ParseException | IOException | Exception e){
					e.printStackTrace();

					setWaitingProcess(false);

					/*
					logger.error("Page " + page + " № " + pageCount + ", " + comboBoxDataType.getValue() + " № " + pageElementCount + ", Parse error msg: " +
							             e.getMessage());*/

					Dialogs.showErrorDialog(e, new DialogText("Ошибка импорта", "Ошибка импорта " + comboBoxDataType.getValue(),
							"Page " + page + " № " + pageCount + ", " + comboBoxDataType.getValue() + " № " + pageElementCount + ", Parse error msg: " +
									e.getMessage()), logger);


					logger.error("URL: " + pageContent);
					logger.error("Объект: " + text);

					textArea.appendText("Page " + page + " № " + pageCount + ", " + comboBoxDataType.getValue() + " № " + pageElementCount + ", Parse error msg: " +
							                    e.getMessage());
					if (transaction != null) {
						transaction.rollback();
					}
					//throw new Exception(e);

				} finally {
					session.close();
					setWaitingProcess(false);

					//textArea.appendText("Найдено " + pageElementCount + " " + comboBoxDataType.getValue() + "\n");
					return pageElementCount;
				}
				/*
				buttonGetData.getParent().getScene().setCursor(Cursor.DEFAULT); //Change cursor to default style
				buttonGetData.getParent().getParent().setDisable(false);
				*/
			}
		};
		return task;
	}


	public void onParsePage(ActionEvent actionEvent) {
		if (urlConnection) {
			String inputText =
					Dialogs.showInputDialog("Разобрать страницу по URl", "Укажите страницу для разбора", "Страница:");
			textArea.appendText("Страница для разбора: " + inputText + "\n");
			if (!inputText.isEmpty() && inputText != null) {

				buttonGetData.getParent().getScene().setCursor(Cursor.WAIT);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							PageContentURL pageContent = Prozorro.getPageContent(new URL(inputText));
							for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
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
			String inputText =
					Dialogs.showInputDialog("Разобрать тендер по ID", "Укажите ID тендера для разбора", "Тендер:");
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
			Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка ", "Ошибка подключенияпо ссылке",
					"Ошибка доступа или ошибка данных с сайта Прозоро");

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
				Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка сохранения",
						"Ошибка при попытке сохранить файл " + file.getAbsoluteFile(), e.getMessage());
			}
		}
	}

	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		this.propertyFields = new PropertyFields(prozorroApp.getProperties());
	}

	private SessionFactory getSessionFactoryByDBName(String dbName) {
		//logger.info("DBName = " + dbName);
		//String dbName = PropertiesUtils.getPropertyString(prozorroApp.getProperties(), "db.type");
		if (dbName == null || dbName.equals("")) {
			return null;
		}
		HibernateDataBaseType baseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		logger.info("HibernateDataBaseType = " + baseType);
		SessionFactory factory = HibernateFactory.getHibernateSession(baseType);
		return factory;
	}

	private void printConnectionResult(SessionFactory sessionFactory) {
		String text;
		if (sessionFactory != null) {
			text = "Подключено к БД" + "\n";
		} else {
			text = "БД не доступна" + "\n";
		}
		logger.info(text);
		textArea.appendText(text);
	}

	public void onMenuItemScheme(ActionEvent actionEvent) {
		boolean okClicked = prozorroApp.showSchemeDialog();
	}
}
