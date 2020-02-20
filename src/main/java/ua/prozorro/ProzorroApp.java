package ua.prozorro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.controller.MainController;
import ua.prozorro.controller.ParseURLController;
import ua.prozorro.controller.SchemaExportDialogController;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.language.Messages;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ProzorroApp extends Application {
	
	private static final String APP_NAME = "Prozorro App";
	private static final String CONFIG_FILE_NAME = "Prozorro.properties";
	private static final Logger logger = LogManager.getRootLogger();
	
	//private Session session;
	
	private Stage primaryStage;
	private BorderPane root;
	
	private Properties properties = new Properties();
	private ResourceBundle messages;
	
	public static void main(String[] args) {
		logger.info("Start");
		launch(args);
		logger.info("Close");
	}
	
	public static String getAppName() {
		return APP_NAME;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
		
		File propertiesFile = FileUtils.getFileWithName(this.getClass(), CONFIG_FILE_NAME);
		logger.info("property File: " + propertiesFile);
		
		try {
			properties = PropertiesUtils.getPropertiesFromFile(propertiesFile);
			
			PropertiesUtils.toString(properties);
			

            /*String dbName = PropertiesUtils.getPropertyString(properties, "db.type");
            session = getSessionByDBName(dbName);*/
			
		} catch (IOException e) {
			//            e.printStackTrace();
			Dialogs.showErrorDialog(e, new DialogText(messages.getString("error.app.start"),
													  messages.getString("error.resources.file"),
													  messages.getString("error.resources.file_cant_find") + " '" +
													  CONFIG_FILE_NAME + "'"), logger);
		}
		
		initResourceBundle();
		
		initMainView();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				if (isConfirmShutDown()) {
					Platform.exit();
					System.exit(0);
				} else {
					we.consume();
				}
			}
		});
	}
	
	private void initResourceBundle() {
		Messages messages = new Messages();
		this.messages = messages.getResourceBundle(PropertiesUtils.getPropertyString(properties, "app.language"));
	}
	
	
	private void initMainView() throws IOException {
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/views/LoginForm.fxml"));*/
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/mainForm.fxml"));
		root = loader.load();
		//Parent root = loader.load();
		
		primaryStage.setTitle(messages.getString("app.title"));
		primaryStage.setScene(new Scene(root));
		//primaryStage.getIcons().add(ImageResources.getAppIcon());
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/prozorro_favicon.png")));
		
		MainController controller = loader.getController();
		
		controller.setProzorroApp(this);
		
		primaryStage.show();
		
	}
	
	public boolean showSchemeDialog() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/SchemaExportDialog.fxml"));
			
			AnchorPane page = loader.load();
			
			// Создаём диалоговое окно Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle(messages.getString("app.forms.scheme_dialog.title"));
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/prozorro_favicon.png")));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			SchemaExportDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProzorroApp(this);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			
			Dialogs.showErrorDialog(e, new DialogText(messages.getString("error.app.start"),
													  messages.getString("error.app.show_dialog"),
													  messages.getString("error.app.dialog_cant_show") +
													  " 'SchemaExportDialog'"), logger);
			return false;
		}
	}
	
	public boolean showParseURLDialog() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/ParseURLDialog.fxml"));
			
			AnchorPane page = loader.load();
			
			// Создаём диалоговое окно Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle(messages.getString("app.forms.parse_URL_dialog.title"));
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/prozorro_favicon.png")));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			ParseURLController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProzorroApp(this);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			
			Dialogs.showErrorDialog(e, new DialogText(messages.getString("error.app.start"),
													  messages.getString("error.app.show_dialog"),
													  messages.getString("error.app.dialog_cant_show") +
													  " 'ParseURLDialog'"), logger);
			return false;
		}
	}
	
	public boolean shutDown() {
		if (isConfirmShutDown()) {
			
			Platform.exit();
			System.exit(0);
			return true;
		}
		return false;
	}
	
	public boolean isConfirmShutDown() {
		return Dialogs.showConfirmDialog(
				new DialogText(messages.getString("app.close.title"), messages.getString("app.close.info"),
							   messages.getString("confirm") + "?"));
	}
	
	public BorderPane getRoot() {
		return root;
	}

	
	public Properties getProperties() {
		return properties;
	}
	
	public URL getURL() {
		return FileUtils.getLocation(this.getClass());
	}
	
	public ResourceBundle getMessages() {
		return messages;
	}
	
}
