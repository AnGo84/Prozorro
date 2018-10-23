package ua.prozorro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.utils.FileUtils;

import java.io.File;

public class ProzorroApp extends Application {

    private static final String APP_NAME = "Prozorro App";
    private static final String CONFIG_FILE_NAME = "Prozorro.properties";
    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/LoginForm.fxml"));*/

        Parent root = FXMLLoader.load(getClass().getResource("/views/mainForm.fxml"));
        primaryStage.setTitle("Данные из портала Prozorro.gov.ua");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
