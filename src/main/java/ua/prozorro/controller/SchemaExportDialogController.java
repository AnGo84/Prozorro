package ua.prozorro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.classloading.spi.ClassLoadingException;
import org.hibernate.service.spi.ServiceException;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import ua.prozorro.ProzorroApp;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.hibernate.HibernateDataBaseType;
import ua.prozorro.hibernate.HibernateSession;
import ua.prozorro.properties.AppResources;
import ua.prozorro.properties.PropertiesUtils;

import java.io.File;
import java.util.EnumSet;

public class SchemaExportDialogController {
	private static final Logger logger = LogManager.getRootLogger();
	
	@FXML
	private ComboBox<HibernateDataBaseType> comboBoxDB;
	@FXML
	private Button buttonOk;
	@FXML
	private Button buttonCancel;
	@FXML
	private Button buttonChooseFile;
	@FXML
	private CheckBox checkBoxSaveScheme;
	@FXML
	private TextField textFieldFileName;
	@FXML
	private Label labelDataBase;
	@FXML
	private Label labelFile;

	private ProzorroApp prozorroApp;
	private AppResources appResources;

	private Stage dialogStage;

	private boolean okClicked = false;

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void initialize() {
		comboBoxDB.getItems().setAll(HibernateDataBaseType.values());
		
		textFieldFileName.setText("");
		
		checkBoxSaveScheme.setSelected(false);
		
	}
	
	public void onButtonChooseFile(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SQL files (*.sql)", "*.sql");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialFileName(HibernateSession.getDefaultScriptFileName(comboBoxDB.getValue().name()));
		
		File file = fileChooser.showSaveDialog(buttonChooseFile.getScene().getWindow());
		if (file != null) {
			textFieldFileName.setText(file.getAbsolutePath());
		}
		logger.info("Selected file: " + textFieldFileName.getText());
	}
	
	public void onButtonOk(ActionEvent actionEvent) {
		
		if (textFieldFileName.getText() != null && !textFieldFileName.getText().equals("")) {
			File file = new File(textFieldFileName.getText());
			
			String tempDialectEngine = System.getProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE);
			if (tempDialectEngine == null || tempDialectEngine.equals("") || !tempDialectEngine.toUpperCase().equals(
					HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE_NAME.toUpperCase())) {
				
				System.setProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE,
								   HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE_NAME);
			}
			
			Metadata metadata = null;
			
			try {
				createSchema(file, metadata);
			}
			catch (Exception e) {
				e.printStackTrace();
				Dialogs.showErrorDialog(e.getCause(),
						new DialogText(appResources.getMessages().getString("error.exporting"),
								e.getMessage(), String.format(
								appResources.getMessages().getString("error" + ".export.DB"),
								comboBoxDB.getValue(), comboBoxDB.getValue().getConfigFileName())),
						logger);
			}
			
			okClicked = true;
			dialogStage.close();
		} else {
			Dialogs.showMessage(Alert.AlertType.WARNING,
					new DialogText(appResources.getMessages().getString("error.file"),
							appResources.getMessages().getString("error.file.verification"),
							appResources.getMessages().getString("error.file.wrong_name_or_path") +
									" '" + textFieldFileName.getText() + "'"), logger);
		}
		
	}
	
	private void createSchema(File file, Metadata metadata) throws Exception {
		try {
			logger.info("Choose file: " + comboBoxDB.getValue().getConfigFileName());
			metadata = HibernateSession.getMetaData(comboBoxDB.getValue().getConfigFileName());
			DialogText dialogText = null;
			if (checkBoxSaveScheme.isSelected()) {
				if (Dialogs.showConfirmDialog(new DialogText(appResources.getMessages().getString("schema.DB.create"),
						appResources.getMessages().getString("schema.DB.create") +
								" " + comboBoxDB.getValue(), appResources.getMessages()
						.getString(
								"message.all_data_will_be_delete")))) {
					SchemaExport schemaExport = HibernateSession.getSchemaExport(file.getAbsolutePath());
					logger.info("Drop Database...");
					// Drop Database
					HibernateSession.dropDataBase(schemaExport, metadata);

					logger.info("Create Database...");
					// Create tables
					HibernateSession.createDataBase(schemaExport, metadata);

					dialogText = new DialogText(appResources.getMessages().getString("schema.DB.create"),
							appResources.getMessages().getString("schema.DB.create") + " " +
									comboBoxDB.getValue(),
							appResources.getMessages().getString("schema.DB.created"));
				}
				
			} else {
				new SchemaExport().setOutputFile(file.getAbsolutePath()).setFormat(false).createOnly(
						EnumSet.of(TargetType.SCRIPT), metadata);
				logger.info("Schema was export to file: " + file.getAbsolutePath());

				dialogText = new DialogText(appResources.getMessages().getString("schema.DB.export"),
						appResources.getMessages().getString("schema.DB.saved_to_file"),
						comboBoxDB.getValue() +
								appResources.getMessages().getString("schema.DB" + ".saved_to_file") + " '" +
								file.getAbsolutePath() + "'");
			}
			Dialogs.showMessage(Alert.AlertType.INFORMATION, dialogText, logger);
			
		}
		catch (ExceptionInInitializerError | ServiceException | ClassLoadingException e) {
			throw new Exception(appResources.getMessages().getString("error.file.configuration"), e);
		}
		catch (RuntimeException e) {
			throw new Exception(appResources.getMessages().getString("error.export.processing"), e);
		}
		catch (Exception e) {
			throw new Exception(appResources.getMessages().getString("error.export.DB"), e);
		}
		finally {
			logger.info("Close connection");
			if (metadata != null) {
				SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
				sessionFactory.close();
			}
		}
	}
	
	public void onButtonCancel(ActionEvent actionEvent) {
		dialogStage.close();
	}
	
	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		this.appResources = prozorroApp.getAppResources();

		String dbName = PropertiesUtils.getPropertyString(appResources.getProperties(), "db.type");
		HibernateDataBaseType dataBaseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		comboBoxDB.getSelectionModel().select(dataBaseType);

		initComponentText();
	}
	
	private void initComponentText() {
		buttonOk.setTooltip(new Tooltip(appResources.getMessages().getString("execute.execute")));
		buttonCancel.setTooltip(new Tooltip(appResources.getMessages().getString("execute.cancel")));
		buttonChooseFile.setTooltip(new Tooltip(appResources.getMessages().getString("file.select")));

		labelDataBase.setText(appResources.getMessages().getString("label.data_base"));
		labelFile.setText(appResources.getMessages().getString("label.file"));
		checkBoxSaveScheme.setText(appResources.getMessages().getString("message.DB.create.schema_from_settings_file"));
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
}
