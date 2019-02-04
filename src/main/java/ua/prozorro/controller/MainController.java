package ua.prozorro.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import ua.prozorro.Prozorro;
import ua.prozorro.ProzorroApp;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.prozorro.model.pages.PageContentURL;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderOld;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.sql.HibernateFactory;
import ua.prozorro.task.TaskFactory;
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
	private ProgressBar progressBar = new ProgressBar(0);
	@FXML
	private ProgressIndicator progressIndicator = new ProgressIndicator(0);
	@FXML
	private MenuItem menuItemParseList;
	@FXML
	private MenuItem menuItemParseTender;
	@FXML
	private MenuItem menuItemParseURL;
	
	@FXML
	private void initialize() {
		buttonGetPages.setTooltip(new Tooltip("Выбрать страницы за указанный период"));
		buttonGetData.setTooltip(new Tooltip("Выбрать тендеры с отобранных страниц"));
		
		resultData = new ParsingResultData();
		
		initListeners();
		
		textArea.appendText("Start" + "\n");
		
		//textArea.appendText("TEXT max length: " + String.valueOf(textArea.getMaxHeight()) + "\n");
		
		datePickerFrom.setValue(LocalDate.now());
		datePickerTill.setValue(LocalDate.now());
		
		comboBoxDataType.getItems().setAll(DataType.values());
		//comboBoxDataType.getItems().setAll(DataType.TENDERS,DataType.PLANS);
		comboBoxDataType.setValue(DataType.TENDERS);
		
	}
	
	private void initListeners() {
		// auto cleaning textAria
       /* textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null && newValue.length() > LOGGIN_TEXT_LIMIT) {
                    newValue = newValue.substring(newValue.length() - (LOGGIN_TEXT_LIMIT + 1));
                }
                textArea.setText(newValue);
				*//*String[] lines = newValue.split("\n", -1);
				if(lines.length>50){
					StringBuffer stringBuffer = new StringBuffer();
					for (int i=0;i <=50;i++){
						stringBuffer.append(lines[lines.length-50 + i]);
					}
					textArea.setText(stringBuffer.toString());
				}else{
					textArea.setText(newValue);
				}*//*
            }
        });*/
		
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
			
			Task<ParsingResultData> task = TaskFactory.taskForCalculationParsingTimeForPeriod(propertyFields);
			
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
					errorHeaderText = "Another, unexpected execption";
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
		return new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            /*System.out.println("Old mes: "+oldValue);
                            System.out.println("New mes: "+newValue);*/
				//String[] lineArray = textArea.getText().split("\n");
				textArea.appendText(newValue);
			}
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
	}
	
	private String checkDataForPeriodMessage() {
		
		String textDateFrom = DateUtils.parseDateToString(dateFrom, DateUtils.DATE_PATTERN_DD_MM_YYYY);
		String textDateTill = DateUtils.parseDateToString(dateTill, DateUtils.DATE_PATTERN_DD_MM_YYYY);
		
		return "Отбор данных по '" + comboBoxDataType.getValue().getTypeName() + "' за период с " + textDateFrom +
			   " по " + textDateTill + "\n";
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
					
					//Task task = getDataAndSave(sessionFactory, propertyFields, resultData);
					Task task = TaskFactory.taskForParseAndSave(sessionFactory, propertyFields, resultData);
					
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
						logger.error(throwable.getStackTrace());
						
						Dialogs.showErrorDialog(task.getException(),
												new DialogText("Ошибка отбора", "Ошибка при проверке данных за период",
															   "При проверке данных по " +
															   comboBoxDataType.getValue().getTypeName() +
															   " за период c " + DateUtils.dateToString(dateFrom) +
															   " по " + DateUtils.dateToString(dateTill) +
															   " возникла ошибка: " + task.getException().getMessage()),
												logger);
						
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
