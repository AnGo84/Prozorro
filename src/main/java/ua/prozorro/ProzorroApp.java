package ua.prozorro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.controller.MainController;
import ua.prozorro.controller.ParseURLController;
import ua.prozorro.controller.SchemaExportDialogController;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.AppResources;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;


public class ProzorroApp extends Application {
	private static final Logger logger = LogManager.getRootLogger();
	
	private Stage primaryStage;
	private BorderPane root;
	
	private AppResources appResources;
	
	public static void main(String[] args) {
		logger.info("Start");
		launch(args);
		logger.info("Close");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		appResources = new AppResources();
		this.primaryStage = primaryStage;
		
		try {
			appResources.initResources(this.getClass());
		}
		catch (IOException e) {
			Dialogs.showErrorDialog(e, new DialogText("Application start error", "Error with resource's file",
													  "Can't find resource's file"), logger);
		}
		
		initMainView();
		
		primaryStage.setOnCloseRequest(we -> {
			if (isConfirmShutDown()) {
				Platform.exit();
				System.exit(0);
			} else {
				we.consume();
			}
		});
	}
	
	
	private void initMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/mainForm.fxml"));
		root = loader.load();
		
		primaryStage.setTitle(getMessages().getString("app.title"));
		primaryStage.setScene(new Scene(root));
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
			dialogStage.setTitle(getMessages().getString("app.forms.scheme_dialog.title"));
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
		}
		catch (IOException e) {
			e.printStackTrace();
			Dialogs.showErrorDialog(e, getDialogTextOnShowError("SchemaExportDialog"), logger);
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
			dialogStage.setTitle(getMessages().getString("app.forms.parse_URL_dialog.title"));
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
		}
		catch (IOException e) {
			e.printStackTrace();
			Dialogs.showErrorDialog(e, getDialogTextOnShowError("ParseURLDialog"), logger);
			return false;
		}
	}
	
	private DialogText getDialogTextOnShowError(String dialogName) {
		return new DialogText(getMessages().getString("error.app.start"),
							  getMessages().getString("error.app.show_dialog"),
							  getMessages().getString("error.app.dialog_cant_show") + " '" + dialogName + "'");
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
				new DialogText(getMessages().getString("app.close.title"), getMessages().getString("app.close.info"),
							   getMessages().getString("confirm") + "?"));
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
	public Properties getProperties() {
		return appResources.getProperties();
	}
	
	public ResourceBundle getMessages() {
		return appResources.getMessages();
	}
	
}
