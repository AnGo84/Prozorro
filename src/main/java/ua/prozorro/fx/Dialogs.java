package ua.prozorro.fx;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Created by AnGo on 07.03.2017.
 */
public class Dialogs {
	private static final String LABEL_STACKTRACE_TEXT = "The exception stacktrace is:";
	
	public static void showMessage(Alert.AlertType alertType, String titleText, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titleText);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		alert.showAndWait();
	}
	
	public static void showMessage(Alert.AlertType alertType, DialogText dialogText, Logger logger) {
		Alert alert = new Alert(alertType);
		alert.setTitle(dialogText.getTitleText());
		alert.setHeaderText(dialogText.getHeaderText());
		alert.setContentText(dialogText.getContentText());
		
		logger.info(alertType + ": " + dialogText + "\n");
		alert.showAndWait();
	}
	
	public static Boolean showConfirmDialog(String titleText, String headerText, String contentText) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(titleText);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean showConfirmDialog(DialogText dialogText) {
		return showConfirmDialog(dialogText.getTitleText(), dialogText.getHeaderText(), dialogText.getContentText());
	}
	
	public static void showErrorDialog(Throwable ex, String titleText, String headerText, String contentText) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(titleText);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();
		
		Label label = new Label("The exception stacktrace was:");
		
		GridPane expContent = getGridPane(exceptionText, label);
		
		alert.getDialogPane().setExpandableContent(expContent);
		
		alert.showAndWait();
	}
	
	private static Alert getAlert(Alert.AlertType alertType, DialogText dialogText) {
		Alert alert = new Alert(alertType);
		
		//        alert.setGraphic(new ImageView(new Dialogs().DIALOG_ICON));
		
		alert.setTitle(dialogText.getTitleText());
		alert.setHeaderText(dialogText.getHeaderText());
		alert.setContentText(dialogText.getContentText());
		
		// Get the Stage.
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		// Add a custom icon.
		//stage.getIcons().add(ImageResources.getAppIcon());
		
		
		return alert;
	}
	
	public static void showErrorDialog(Throwable ex, DialogText dialogText, Logger logger) {
		
		Alert alert = getAlert(Alert.AlertType.ERROR, dialogText);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = dialogText.getContentText() + " : " + sw.toString();
		if (logger != null) {
			logger.error(dialogText + "\n. Error message: \n" + sw.toString());
		}
		
		Label label = new Label(LABEL_STACKTRACE_TEXT);
		
		GridPane expContent = getGridPane(exceptionText, label);
		
		alert.getDialogPane().setExpandableContent(expContent);
		
		alert.showAndWait();
	}
	
	private static GridPane getGridPane(String exceptionText, Label label) {
		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		
		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);
		return expContent;
	}
	
	public static String showInputDialog(String titleText, String headerText, String contentText) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(titleText);
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			return "";
		}
	}
	
	public static File getTXTFileFromOpenDialog(Window window) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		return fileChooser.showSaveDialog(window);
	}
}
