package ua.prozorro.controller;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.SessionFactory;
import ua.prozorro.ProzorroApp;
import ua.prozorro.fx.CallBackFrom;
import ua.prozorro.fx.CallBackTill;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.hibernate.HibernateDataBaseType;
import ua.prozorro.hibernate.HibernateFactory;
import ua.prozorro.hibernate.HibernateSession;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceLinkFactory;
import ua.prozorro.source.SourceType;
import ua.prozorro.task.ExportDataTask;
import ua.prozorro.task.TaskResult;
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
	
	private ProzorroApp prozorroApp;
	
	private TaskResult taskResult;
	
	@FXML
	private Button buttonProcess;
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
	private ComboBox<SourceType> comboBoxSourceType;
	
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
	private Menu menuCheck;
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
		textArea.appendText("Start" + "\n");
		initDatePicker();
		comboBoxSourceType.getItems().setAll(SourceType.values());
		comboBoxSourceType.setValue(SourceType.PROZORRO_TENDER);
		
	}
	
	private void initDatePicker() {
		datePickerFrom.setValue(LocalDate.now());
		datePickerFrom.setDayCellFactory(new CallBackFrom());
		datePickerTill.setValue(LocalDate.now());
		datePickerTill.setDayCellFactory(new CallBackTill(datePickerFrom));
	}
	
	private ChangeListener<String> getTextAreaListener() {
		return (observable, oldValue, newValue) -> {
			textArea.appendText(newValue);
		};
	}
	
	private void setWaitingProcess(boolean isWaiting) {
		progressBar.progressProperty().unbind();
		progressIndicator.progressProperty().unbind();
		textArea.textProperty().unbind();
		
		prozorroApp.getRoot().setDisable(isWaiting);
		
		buttonProcess.getParent().getParent().setDisable(isWaiting);
		buttonProcess.getParent().getScene().setCursor(
				(isWaiting) ? Cursor.WAIT : Cursor.DEFAULT); //Change cursor to wait style
	}
	
	private String checkDataForPeriodMessage() {
		
		String textDateFrom = DateUtils.parseDateToString(dateFrom, DateUtils.DATE_PATTERN_DD_MM_YYYY);
		String textDateTill = DateUtils.parseDateToString(dateTill, DateUtils.DATE_PATTERN_DD_MM_YYYY);
		
		return String.format(prozorroApp.getMessages().getString("message.check_data_for_period") + "\n",
							 comboBoxSourceType.getValue(), textDateFrom, textDateTill);
	}
	
	public void onButtonProcess(ActionEvent actionEvent) {
		if (DateUtils.isValidForPeriod(datePickerFrom.getValue(), datePickerTill.getValue())) {
			dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			textArea.appendText(checkDataForPeriodMessage());
			
			if (Dialogs.showConfirmDialog(prozorroApp.getMessages().getString("confirm"),
										  prozorroApp.getMessages().getString("message.select_data") + " " +
										  comboBoxSourceType.getValue(), String.format(
							prozorroApp.getMessages().getString("message.check_data_for_period_confirm"),
							comboBoxSourceType.getValue(), DateUtils.dateToString(dateFrom),
							DateUtils.dateToString(dateTill)))) {
				setWaitingProcess(true);
				
				progressBar.setProgress(0);
				progressIndicator.setProgress(0);
				
				FilterData filterData = new FilterData(dateFrom, dateTill, true, false);
				try {
					SessionFactory sessionFactory = null;
					if (!filterData.isReadOnly()) {
						sessionFactory = getSessionFactoryByDBName(PropertiesUtils.getPropertyString(
								prozorroApp.getProperties(), HibernateSession.DB_TYPE));
					}
					
					SourceLink sourceLink = SourceLinkFactory.getSourceLink(comboBoxSourceType.getValue(),
																			prozorroApp.getProperties());
					startProcessingTask(sourceLink, filterData, sessionFactory);
				}
				catch (ExceptionInInitializerError e) {
					logger.error("DB connection error", e);
					setWaitingProcess(false);
					Dialogs.showErrorDialog(e,
											new DialogText(prozorroApp.getMessages().getString("error.db.connection"),
														   prozorroApp.getMessages()
																	  .getString("error.db.connection.message"),
														   prozorroApp.getMessages()
																	  .getString("error.db.connection.message") + "l " +
														   e.getMessage()), logger);
					
				}
				
			}
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING, prozorroApp.getMessages().getString("warning"),
								prozorroApp.getMessages().getString("error.date.dates_set"),
								String.format(prozorroApp.getMessages().getString("error.date.dates_set_message"),
											  datePickerFrom.getValue(), datePickerTill.getValue()));
			datePickerFrom.requestFocus();
		}
	}
	
	private void startProcessingTask(SourceLink sourceLink, FilterData filterData, SessionFactory sessionFactory) {
		Task task = new ExportDataTask(sourceLink, filterData, sessionFactory);
		
		task.setOnRunning((e) -> {
			task.messageProperty().addListener(getTextAreaListener());
			
			progressBar.progressProperty().bind(task.progressProperty());
			progressIndicator.progressProperty().bind(task.progressProperty());
			
			logger.info("Start parsing " + comboBoxSourceType.getValue() + " for period from " +
						DateUtils.dateToString(dateFrom) + " till " + DateUtils.dateToString(dateTill));
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
																 comboBoxSourceType.getValue(),
																 DateUtils.dateToString(dateFrom),
																 DateUtils.dateToString(dateTill),
																 task.getException().getMessage())), null);
			setWaitingProcess(false);
			progressBar.progressProperty().unbind();
			progressIndicator.progressProperty().unbind();
			progressBar.setProgress(0);
			progressIndicator.setProgress(0);
		});
		
		task.setOnSucceeded((e) -> {
			taskResult = (TaskResult) task.getValue();
			String mess = String.format(prozorroApp.getMessages().getString("message.execution_finished"),
										comboBoxSourceType.getValue(), DateUtils
												.parseDateToString(filterData.getDateFrom(),
																   filterData.getDateFormat()), DateUtils
												.parseDateToString(filterData.getDateTill(),
																   filterData.getDateFormat()),
										taskResult.getTotalCount(), DateUtils.getTextTime(taskResult.getTotalTime()));
			
			logger.info(mess);
			textArea.appendText(mess + "\n");
			setWaitingProcess(false);
		});
		Thread th = new Thread(task);
		th.start();
	}
	
	public void onCleanLog(ActionEvent actionEvent) {
		if (Dialogs.showConfirmDialog(prozorroApp.getMessages().getString("log.clean"),
									  prozorroApp.getMessages().getString("log.clean.message"),
									  prozorroApp.getMessages().getString("log.clean.confirm_message"))) {
			textArea.clear();
		}
	}
	
	public void onClose(ActionEvent actionEvent) {
		prozorroApp.shutDown();
	}
	
	public void onSaveLogToFile(ActionEvent actionEvent) {
		File file = Dialogs.getTXTFileFromOpenDialog(buttonProcess.getScene().getWindow());
		
		if (file != null) {
			textArea.appendText("Choose file for saving: " + file.getAbsoluteFile() + "\n");
			try {
				FileUtils.saveToFile(textArea.getText(), file);
			}
			catch (IOException e) {
				Dialogs.showMessage(Alert.AlertType.WARNING, prozorroApp.getMessages().getString("error.file.saving"),
									prozorroApp.getMessages().getString("error.file.saving_message") + " " +
									file.getAbsoluteFile(), e.getMessage());
			}
		}
	}
	
	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		initComponentText();
	}
	
	private void initComponentText() {
		buttonProcess.setText(prozorroApp.getMessages().getString("label.process"));
		labelSelectSource.setText(prozorroApp.getMessages().getString("label.select.source"));
		labelPeriodFrom.setText(prozorroApp.getMessages().getString("label.period_from"));
		labelTo.setText(prozorroApp.getMessages().getString("label.to"));
		labelProgress.setText(prozorroApp.getMessages().getString("label.progress"));
		
		menuMenu.setText(prozorroApp.getMessages().getString("menu.menu"));
		menuSchemaDB.setText(prozorroApp.getMessages().getString("menu.schema_DB"));
		menuLog.setText(prozorroApp.getMessages().getString("menu.log"));
		menuCheck.setText(prozorroApp.getMessages().getString("menu.check"));
		
		menuItemClose.setText(prozorroApp.getMessages().getString("close"));
		menuItemExportSchemeDB.setText(prozorroApp.getMessages().getString("menu.items.export_scheme_DB"));
		menuItemParseURL.setText(prozorroApp.getMessages().getString("menu.items.parse_URL"));
		menuItemCleanLog.setText(prozorroApp.getMessages().getString("menu.items.clear_log"));
		menuItemSaveLogToFile.setText(prozorroApp.getMessages().getString("menu.items.save_to_file"));
	}
	
	private SessionFactory getSessionFactoryByDBName(String dbName) throws ExceptionInInitializerError {
		if (Strings.isBlank(dbName)) {
			return null;
		}
		HibernateDataBaseType baseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		logger.info("HibernateDataBaseType: " + baseType);
		textArea.appendText("Data Base Type: " + baseType + "\n");
		URL url = FileUtils.getLocation(ProzorroApp.class);
		
		logger.info("Path to config files: " + url.getPath());
		textArea.appendText("Path to config files: " + url.getPath() + "\n");
		
		SessionFactory factory = HibernateFactory.getHibernateSession(url, baseType);
		String connectionResult = (factory == null) ? "DB connection missing" : "Connected to DB";
		
		logger.info(connectionResult + " \n");
		textArea.appendText(connectionResult + "\n");
		return factory;
	}
	
	public void onMenuItemScheme(ActionEvent actionEvent) {
		boolean okClicked = prozorroApp.showSchemeDialog();
	}
	
	public void onMenuItemParseURL(ActionEvent actionEvent) {
		boolean okClicked = prozorroApp.showParseURLDialog();
	}
}
