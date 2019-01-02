package ua.prozorro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.ProzorroApp;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sql.HibernateDataBaseType;

import java.io.File;

public class SchemeDialogController {
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

    private ProzorroApp prozorroApp;
    private PropertyFields propertyFields;

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

        buttonOk.setTooltip(new Tooltip("Выполнить"));
        buttonCancel.setTooltip(new Tooltip("Отменить выгрузку"));
        buttonChooseFile.setTooltip(new Tooltip("Выбрать файл"));

        textFieldFileName.setText("");

        checkBoxSaveScheme.setSelected(false);

    }

    public void onButtonChooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SQL files (*.sql)", "*.sql");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(buttonChooseFile.getScene().getWindow());
        if (file != null) {
            textFieldFileName.setText(file.getAbsolutePath());
        }
        logger.info("Выбран файл: " + textFieldFileName.getText());
    }

    public void onButtonOk(ActionEvent actionEvent) {
        okClicked = true;
    }

    public void onButtonCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setProzorroApp(ProzorroApp prozorroApp) {
        this.prozorroApp = prozorroApp;
        this.propertyFields = new PropertyFields(prozorroApp.getProperties());

        String dbName = PropertiesUtils.getPropertyString(prozorroApp.getProperties(), "db.type");
        HibernateDataBaseType dataBaseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
        comboBoxDB.getSelectionModel().select(dataBaseType);

    }

    public boolean isOkClicked() {
        return okClicked;
    }
}
