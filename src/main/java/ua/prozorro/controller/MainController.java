package ua.prozorro.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ua.prozorro.ProzorroApp;
import ua.prozorro.fx.CallBackFrom;
import ua.prozorro.fx.CallBackTill;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceLinkFactory;
import ua.prozorro.source.SourceType;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.sql.HibernateFactory;
import ua.prozorro.task.DataParseAndSaveTask;
import ua.prozorro.task.ExportDataTask;
import ua.prozorro.task.TaskResult;
import ua.prozorro.task.TimeMeasureTask;
import ua.prozorro.temp.TestPropertyFieldsFactory;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.urlreader.FilterData;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainController {
	private static final Logger logger = LogManager.getRootLogger();
	
	private Date dateFrom;
	private Date dateTill;
	
	private Boolean urlConnection = false;
	private ProzorroApp prozorroApp;
	
	private PropertyFields propertyFields;
	
	private ParsingResultData resultData;
	
	private TaskResult taskResult;
	
	@FXML
	private Button buttonGetPages;
	@FXML
	private Button buttonGetData;
	@FXML
	private Button buttonData;
	@FXML
	private Button buttonDownload;
	@FXML
	private Label labelTotalPages;
	@FXML
	private Label labelSelectSource;
	@FXML
	private Label labelPeriodFrom;
	@FXML
	private Label labelTo;
	@FXML
	private Label labelProgress;
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private ComboBox<DataType> comboBoxDataType;
	
	@FXML
	private DatePicker datePickerFrom;
	@FXML
	private DatePicker datePickerTill;
	@FXML
	private ProgressBar progressBar = new ProgressBar(0);
	@FXML
	private ProgressIndicator progressIndicator = new ProgressIndicator(0);
	@FXML
	private Menu menuMenu;
	@FXML
	private Menu menuSchemaDB;
	@FXML
	private Menu menuLog;
	@FXML
	private Menu menuProcessing;
	@FXML
	private MenuItem menuItemParseList;
	@FXML
	private MenuItem menuItemParseTender;
	@FXML
	private MenuItem menuItemParseURL;
	@FXML
	private MenuItem menuItemClose;
	@FXML
	private MenuItem menuItemExportSchemeDB;
	@FXML
	private MenuItem menuItemCleanLog;
	@FXML
	private MenuItem menuItemSaveLogToFile;
	
	
	@FXML
	private void initialize() {
		buttonGetPages.setTooltip(new Tooltip("Выбрать страницы за указанный период"));
		buttonGetData.setTooltip(new Tooltip("Выбрать данные с отобранных страниц"));
		
		resultData = new ParsingResultData();
		
		initListeners();
		
		textArea.appendText("Start" + "\n");
		
		//textArea.appendText("TEXT max length: " + String.valueOf(textArea.getMaxHeight()) + "\n");
		
		initDatePicker();
		
		comboBoxDataType.getItems().setAll(DataType.values());
		//comboBoxDataType.getItems().setAll(DataType.TENDERS,DataType.PLANS);
		comboBoxDataType.setValue(DataType.TENDERS);
		
		//buttonDownload.setDisable(true);
	}
	
	private void initDatePicker() {
		datePickerFrom.setValue(LocalDate.now());
		datePickerFrom.setDayCellFactory(new CallBackFrom());
		datePickerTill.setValue(LocalDate.now());
		datePickerTill.setDayCellFactory(new CallBackTill(datePickerFrom));
	}
	
	private void initListeners() {
		comboBoxDataType.valueProperty().addListener((ov, t, t1) -> {
			if (t != null && t1 != null && !t.equals(t1)) {
				resultData = new ParsingResultData();
				buttonGetData.setDisable(true);
			}
		});
	}
	
	public void onCheckDataForPeriod(ActionEvent actionEvent) {
		String checkDate = DateUtils.checkDatesForPeriod(datePickerFrom.getValue(), datePickerTill.getValue());
		textArea.clear();
		if (checkDate == null) {
			progressBar.setProgress(0);
			progressIndicator.setProgress(0);
			setWaitingProcess(true);
			//buttonGetData.setDisable(true);
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			textArea.appendText(checkDataForPeriodMessage());

            /*Task<ParsingResultData> task = new Task<ParsingResultData>() {
                @Override
                protected ParsingResultData call() throws IOException, ParseException {
                    ParsingResultData resultData = ProzorroServiceFactory
                            .getApproximatelyParsingTimeForPeriod(propertyFields, comboBoxDataType.getValue(), dateFrom,
                                                                  dateTill);
                    return resultData;
                }
            };*/
			
			propertyFields.setSearchDateFrom(dateFrom);
			//dateTill = pageServiceProzorro.getDateTill(dateTill);
			propertyFields.setSearchDateTill(dateTill);
			propertyFields.setSearchDateType(comboBoxDataType.getValue());
			
			//Task<ParsingResultData> task = TaskFactory.taskForCalculationParsingTimeForPeriod(propertyFields);
			Task<ParsingResultData> task = new TimeMeasureTask(propertyFields);
			
			task.setOnRunning(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					//setWaitingProcess(true);
					task.messageProperty().addListener(getTextAreaListener());
					
				}
			});
			
			task.setOnFailed((e) -> {
				String errorHeaderText;
				task.getException().printStackTrace();
				if (task.getException() instanceof ParseException) {
					errorHeaderText = "Parsing error";
				} else if (task.getException() instanceof IOException) {
					errorHeaderText = "Get data error";
				} else {
					errorHeaderText = "Another, unexpected exception";
				}
				
				setWaitingProcess(false);
				textArea.appendText(errorHeaderText + ": " + task.getException().getMessage() + "\n");
				Dialogs.showErrorDialog(task.getException(), "Exception Dialog", errorHeaderText,
										task.getException().getMessage());
				resultData = new ParsingResultData();
			});
			
			task.setOnSucceeded((e) -> {
				resultData = task.getValue();

                /*String mess = "Найдено страниц с " + comboBoxDataType.getValue() + ": " + resultData.getListSize() +
                              ". Приблизительное время обработки: " +
                              DateUtils.getTextTime(resultData.getParsingTime() / 1000000);*/
				String mess = "Найдено страниц с " + comboBoxDataType.getValue().getTypeName() + ": " +
							  resultData.getListSize() + ". Приблизительное время обработки: " +
							  DateUtils.getTextTime(resultData.getParsingTime());
				setWaitingProcess(false);
				logger.info(mess);
				textArea.appendText(mess + "\n");
				
			});
			Thread th = new Thread(task);
			//th.setDaemon(true);
			th.start();
			
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Даты установлены не верно!", checkDate);
			datePickerFrom.requestFocus();
		}
	}
	
	private ChangeListener<String> getTextAreaListener() {
		return (observable, oldValue, newValue) -> {
                        /*System.out.println("Old mes: "+oldValue);
                        System.out.println("New mes: "+newValue);*/
			//String[] lineArray = textArea.getText().split("\n");
			textArea.appendText(newValue);
		};
	}
	
	private void setWaitingProcess(boolean isWaiting) {
		progressBar.progressProperty().unbind();
		progressIndicator.progressProperty().unbind();
		textArea.textProperty().unbind();
		
		prozorroApp.getRoot().setDisable(isWaiting);
		
		buttonGetPages.getParent().getParent().setDisable(isWaiting);
		buttonGetPages.getParent().getScene()
					  .setCursor((isWaiting) ? Cursor.WAIT : Cursor.DEFAULT); //Change cursor to wait style
		
		buttonGetData.setDisable(isWaiting || !resultData.isHasData());
		
		//buttonDownload.setDisable(isWaiting || (taskResult == null || taskResult.getTotalCount() == 0));
	}
	
	private String checkDataForPeriodMessage() {
		
		String textDateFrom = DateUtils.parseDateToString(dateFrom, DateUtils.DATE_PATTERN_DD_MM_YYYY);
		String textDateTill = DateUtils.parseDateToString(dateTill, DateUtils.DATE_PATTERN_DD_MM_YYYY);
		
		return String.format(prozorroApp.getMessages().getString("message.check_data_for_period") + "\n",
							 comboBoxDataType.getValue().getTypeName(), textDateFrom, textDateTill);
	}
	
	public void onGetParseSaveData(ActionEvent actionEvent) {
		progressBar.setProgress(0);
		progressIndicator.setProgress(0);
		if (resultData.isHasData()) {
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			if (Dialogs.showConfirmDialog("Подтвердите действие", "Примерное время выполнения " +
																  DateUtils.getTextTime(resultData.getParsingTime()),
										  "Время отбора за период с " + DateUtils.dateToString(dateFrom) + "\n по " +
										  DateUtils.dateToString(dateTill) + " " +
										  DateUtils.getTextTime(resultData.getParsingTime()) + ". Продолжить?")) {
				setWaitingProcess(true);
				textArea.clear();
				textArea.appendText(checkDataForPeriodMessage());
				SessionFactory sessionFactory = null;
				try {
					sessionFactory = getSessionFactoryByDBName(
							PropertiesUtils.getPropertyString(propertyFields.getProperties(), "db.type"));
				} catch (ExceptionInInitializerError e) {
					Dialogs.showErrorDialog(e, new DialogText("Ошибка подключения", "Ошибка при подключении к БД",
															  "При подключении к БД возникла ошибка: " +
															  e.getMessage()), logger);
					setWaitingProcess(false);
				}
				//printConnectionResult(sessionFactory);
				
				if (sessionFactory != null) {
					logger.info("Подключено к БД \n");
					textArea.appendText("Подключено к БД \n");
					
					propertyFields.setSearchDateFrom(dateFrom);
					//dateTill = pageServiceProzorro.getDateTill(dateTill);
					propertyFields.setSearchDateTill(dateTill);
					propertyFields.setSearchDateType(comboBoxDataType.getValue());
					
					//Task task = TaskFactory.taskForParseAndSave(sessionFactory, propertyFields, resultData);
					Task task = new DataParseAndSaveTask(sessionFactory, propertyFields, resultData);
					
					task.setOnRunning((e) -> {
						//setWaitingProcess(true);
						
						//textArea.textProperty().bind(task.messageProperty());
						task.messageProperty().addListener(getTextAreaListener());
						
						progressBar.progressProperty().bind(task.progressProperty());
						progressIndicator.progressProperty().bind(task.progressProperty());
						
						logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " +
									DateUtils.dateToString(dateTill));
					});
					
					task.setOnFailed((e) -> {
						Throwable throwable = task.getException();
						textArea.appendText("Error: " + task.getException().getMessage() + "\n");
						
						logger.error("Exception type: " + throwable.getClass());
						//logger.error("Exception StackTrace: ", throwable.getStackTrace());
						logger.error("Exception StackTrace: ", throwable);
						
						Dialogs.showErrorDialog(task.getException(),
												new DialogText("Ошибка отбора", "Ошибка при проверке данных за период",
															   "При проверке данных по " +
															   comboBoxDataType.getValue().getTypeName() +
															   " за период c " + DateUtils.dateToString(dateFrom) +
															   " по " + DateUtils.dateToString(dateTill) +
															   " возникла ошибка: " + task.getException().getMessage()),
												null);
						
						setWaitingProcess(false);
					});
					
					task.setOnSucceeded((e) -> {
						task.getValue();
						String mess = "Окончен отбор";
						logger.info(mess);
						setWaitingProcess(false);
						textArea.appendText(mess + "\n");
					});
					Thread th = new Thread(task);
					th.start();
				} else {
					logger.info("БД не доступна \n");
					textArea.appendText("БД не доступна \n");
					Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет подключения к БД!",
										"Для указанных настроек нет соединения с Базой данных");
				}
			}
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет данных для разбора!",
								"За указанный период нет данных по " + comboBoxDataType.getValue() + " для разбора.");
		}
	}
	
	
	public void onButtonData(ActionEvent actionEvent) {
		String checkDate = DateUtils.checkDatesForPeriod(datePickerFrom.getValue(), datePickerTill.getValue());
		textArea.clear();
		if (checkDate == null) {
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			setWaitingProcess(true);
			textArea.appendText(checkDataForPeriodMessage());
			
			SourceLink sourceLink = SourceLinkFactory.getSourceLink(getSourceType(comboBoxDataType.getValue()),
																	TestPropertyFieldsFactory.getStartProperties());
			FilterData readFilterData = new FilterData(dateFrom, dateTill, false, true);
			//Task task = getTask(sourceLink, readFilterData, null, null);
			startTask(sourceLink, readFilterData, null);
			
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, prozorroApp.getMessages().getString("warning"),
								prozorroApp.getMessages().getString("message.dates.wrong_setting"), checkDate);
			datePickerFrom.requestFocus();
		}
	}
	
	public void onButtonDownload(ActionEvent actionEvent) {
		String checkDate = DateUtils.checkDatesForPeriod(datePickerFrom.getValue(), datePickerTill.getValue());
		
		if (checkDate == null) {
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			setWaitingProcess(true);
			textArea.appendText(checkDataForPeriodMessage());
			
			//if (taskResult != null && taskResult.getTotalCount() > 0) {
			/*if (Dialogs.showConfirmDialog("Подтвердите действие", "Примерное время выполнения " +
																  DateUtils.getTextTime(taskResult.getProcessingTime()),
										  "Время отбора за период с " + DateUtils.dateToString(dateFrom) + "\n по " +
										  DateUtils.dateToString(dateTill) + " " +
										  DateUtils.getTextTime(taskResult.getProcessingTime()) + ". Продолжить?")) {*/
			if (Dialogs.showConfirmDialog("Подтвердите действие", "Отобрать данные по " + comboBoxDataType.getValue(),
										  "Отбор " + comboBoxDataType.getValue() + "\n за период с " +
										  DateUtils.dateToString(dateFrom) + " по " + DateUtils.dateToString(dateTill) +
										  ". Продолжить?")) {
				setWaitingProcess(true);
				progressBar.setProgress(0);
				progressIndicator.setProgress(0);
				//textArea.clear();
				textArea.appendText(checkDataForPeriodMessage());
				SessionFactory sessionFactory = null;
				try {
					sessionFactory = getSessionFactoryByDBName(
							PropertiesUtils.getPropertyString(propertyFields.getProperties(), "db.type"));
				} catch (ExceptionInInitializerError e) {
					logger.error("DB connection error", e);
					Dialogs.showErrorDialog(e, new DialogText("Ошибка подключения", "Ошибка при подключении к БД",
															  "При подключении к БД возникла ошибка: " +
															  e.getMessage()), logger);
					setWaitingProcess(false);
				}
				
				if (sessionFactory != null) {
					logger.info("Connected to DB \n");
					textArea.appendText("Подключено к БД \n");
					SourceLink sourceLink = SourceLinkFactory.getSourceLink(getSourceType(comboBoxDataType.getValue()),
																			TestPropertyFieldsFactory
																					.getStartProperties());
					FilterData saveFilterData = new FilterData(dateFrom, dateTill, true, false);
					startTask(sourceLink, saveFilterData, sessionFactory);
				} else {
					logger.info("Нет подключения к БД \n");
					textArea.appendText("Нет подключения к БД \n");
					Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет подключения к БД!",
										"Для указанных настроек нет соединения с Базой данных");
				}
			}
			//}
			
			
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Даты установлены не верно!", checkDate);
			datePickerFrom.requestFocus();
		}
	}
	
	private SourceType getSourceType(DataType dataType) {
		switch (dataType) {
			case TENDERS:
				return SourceType.PROZORRO_TENDER;
			case CONTRACTS:
				return SourceType.PROZORRO_CONTRACT;
			case PLANS:
				return SourceType.PROZORRO_PLAN;
			case NBU_RATES:
				return SourceType.NBU_RATE;
		}
		return null;
	}
	
	private void startTask(SourceLink sourceLink, FilterData filterData, SessionFactory sessionFactory) {
		Task task = new ExportDataTask(sourceLink, filterData, sessionFactory);
		
		task.setOnRunning((e) -> {
			task.messageProperty().addListener(getTextAreaListener());
			
			progressBar.progressProperty().bind(task.progressProperty());
			progressIndicator.progressProperty().bind(task.progressProperty());
			
			logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " +
						DateUtils.dateToString(dateTill));
		});
		
		task.setOnFailed((e) -> {
			taskResult = null;
			Throwable throwable = task.getException();
			String errorHeaderText;
			if (task.getException() instanceof ParseException) {
				errorHeaderText = "Parsing error";
			} else if (task.getException() instanceof IOException) {
				errorHeaderText = "Get data error";
			} else {
				errorHeaderText = "Another, unexpected exception";
			}
			
			textArea.appendText("Error: " + task.getException().getMessage() + "\n");
			logger.error("Exception type: {}", throwable.getClass());
			logger.error("Exception StackTrace: ", throwable);
			
			Dialogs.showErrorDialog(task.getException(),
									new DialogText(prozorroApp.getMessages().getString("error.error"), errorHeaderText,
												   String.format(prozorroApp.getMessages()
																			.getString("error" + ".execution.message"),
																 comboBoxDataType.getValue().getTypeName(),
																 DateUtils.dateToString(dateFrom),
																 DateUtils.dateToString(dateTill),
																 task.getException().getMessage())), null);
			
			setWaitingProcess(false);
		});
		
		task.setOnSucceeded((e) -> {
			taskResult = (TaskResult) task.getValue();
			String mess = String.format(prozorroApp.getMessages().getString("message.execution_finished"),
										comboBoxDataType.getValue().getTypeName(), DateUtils
												.parseDateToString(filterData.getDateFrom(),
																   filterData.getDateFormat()), DateUtils
												.parseDateToString(filterData.getDateTill(),
																   filterData.getDateFormat()),
										taskResult.getTotalCount(), DateUtils.getTextTime(taskResult.getTotalTime()));
			
			labelTotalPages.setText(String.valueOf(taskResult.getTotalCount()));
			logger.info(mess);
			textArea.appendText(mess + "\n");
			setWaitingProcess(false);
		});
		Thread th = new Thread(task);
		th.start();
		//return task;
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
						/*try {
							PageContentURL pageContent = ProzorroApp.getPageContent(new URL(inputText));
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
						buttonGetData.getParent().getScene().setCursor(Cursor.DEFAULT);*/
					}
				});
			}
		} else
			Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка ", "Ошибка подключенияпо ссылке",
								"Ошибка доступа или ошибка данных с сайта Прозоро");
	}
	
	public void onCleanLog(ActionEvent actionEvent) {
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
						/*try {
							TenderOld tender = Prozorro.getTender(inputText);
							textArea.appendText(tender.toString() + "\n");
						} catch (IOException e) {
							textArea.appendText(e.getMessage() + "\n");
							Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка доступа по ID", e.getMessage());
						}
						buttonGetData.getParent().getScene().setCursor(Cursor.DEFAULT);*/
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
			textArea.appendText("Выбран файл для сохранения журнала: " + file.getAbsoluteFile() + "\n");
			try {
				FileUtils.saveToFile(textArea.getText(), file);
			} catch (IOException e) {
				Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка сохранения",
									"Ошибка при попытке сохранить файл " + file.getAbsoluteFile(), e.getMessage());
			}
		}
	}
	
	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		this.propertyFields = new PropertyFields(prozorroApp.getProperties());
		
		//TODO
		labelSelectSource.setText(prozorroApp.getMessages().getString("label.select.source"));
		labelPeriodFrom.setText(prozorroApp.getMessages().getString("label.period_from"));
		labelTo.setText(prozorroApp.getMessages().getString("label.to"));
		labelProgress.setText(prozorroApp.getMessages().getString("label.progress"));
		
		menuMenu.setText(prozorroApp.getMessages().getString("menu.menu"));
		menuSchemaDB.setText(prozorroApp.getMessages().getString("menu.schema_DB"));
		menuLog.setText(prozorroApp.getMessages().getString("menu.log"));
		menuProcessing.setText(prozorroApp.getMessages().getString("menu.processing"));
		
		menuItemClose.setText(prozorroApp.getMessages().getString("close"));
		menuItemExportSchemeDB.setText(prozorroApp.getMessages().getString("menu.items.export_scheme_DB"));
		menuItemParseURL.setText(prozorroApp.getMessages().getString("menu.items.parse_URL"));
		menuItemCleanLog.setText(prozorroApp.getMessages().getString("menu.items.clear_log"));
		menuItemSaveLogToFile.setText(prozorroApp.getMessages().getString("menu.items.save_to_file"));
		
	}
	
	private SessionFactory getSessionFactoryByDBName(String dbName) throws ExceptionInInitializerError {
		//logger.info("DBName = " + dbName);
		//String dbName = PropertiesUtils.getPropertyString(prozorroApp.getProperties(), "db.type");
		if (dbName == null || dbName.equals("")) {
			return null;
		}
		HibernateDataBaseType baseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		logger.info("HibernateDataBaseType: " + baseType);
		textArea.appendText("Data Base Type: " + baseType + "\n");
		URL url = FileUtils.getLocation(ProzorroApp.class);
		
		logger.info("Path to config files: " + url.getPath());
		textArea.appendText("Path to config files: " + url.getPath() + "\n");
		
		SessionFactory factory = HibernateFactory.getHibernateSession(url, baseType);
		
		//SessionFactory factory = HibernateFactory.getHibernateSession(baseType);
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
	
	public void onMenuItemParseURL(ActionEvent actionEvent) {
		boolean okClicked = prozorroApp.showParseURLDialog();
	}
}
