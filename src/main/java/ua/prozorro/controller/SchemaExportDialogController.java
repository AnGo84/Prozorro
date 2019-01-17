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
import ua.prozorro.ProzorroApp;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.sql.HibernateSession;

import java.io.File;

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
        fileChooser.setInitialFileName(getDefaultFileName(comboBoxDB.getValue().name()));

        File file = fileChooser.showSaveDialog(buttonChooseFile.getScene().getWindow());
        if (file != null) {
            textFieldFileName.setText(file.getAbsolutePath());
        }
        logger.info("Выбран файл: " + textFieldFileName.getText());
    }

    private String getDefaultFileName(String name) {
        if (name != null && !(name.trim()).equals("")) {
            return (name.trim()) + "_" + HibernateSession.SCRIPT_FILE;
        }
        return HibernateSession.SCRIPT_FILE;
    }

    public void onButtonOk(ActionEvent actionEvent) {

        if (textFieldFileName.getText() != null && !textFieldFileName.getText().equals("")) {
            File file = new File(textFieldFileName.getText());
            //if (file.exists() && !file.isDirectory()) {

            String tempDialectEngine = System.getProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE);
            if (tempDialectEngine == null || tempDialectEngine.equals("") || !tempDialectEngine.toUpperCase()
                    .equals(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE_NAME.toUpperCase())) {

                System.setProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE,
                                   HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE_NAME);
            }

            Metadata metadata = null;

            try {
                metadata = HibernateSession.getMetaData(comboBoxDB.getValue().getConfigFileName());

                SchemaExport export = HibernateSession.getSchemaExport(file.getAbsolutePath());
                logger.info("Schema was export to file: " + file.getAbsolutePath());

                Dialogs.showMessage(Alert.AlertType.INFORMATION, new DialogText("Экспорт схемы БД",
                                                                                "Схема БД сохранена в файл '" +
                                                                                file.getName() + "'", "Схема для БД '" +
                                                                                                      comboBoxDB
                                                                                                              .getValue() +
                                                                                                      "' сохранена в файл '" +
                                                                                                      file.getAbsolutePath() +
                                                                                                      "'"), logger);

                if (checkBoxSaveScheme.isSelected()) {
                    if (Dialogs.showConfirmDialog(
                            new DialogText("Создать схему БД", "Создать схему БД " + comboBoxDB.getValue(),
                                           "Все данные будут удалены! Продолжить?"))) {

                        logger.info("Drop Database...");
                        // Drop Database
                        HibernateSession.dropDataBase(export, metadata);

                        logger.info("Create Database...");
                        // Create tables
                        HibernateSession.createDataBase(export, metadata);

                        Dialogs.showMessage(Alert.AlertType.INFORMATION,
                                            new DialogText("Создание схемы БД", "Схема БД создана",
                                                           "Схема для БД '" + comboBoxDB.getValue() + "' создана '"),
                                            logger);
                    }

                }
            } catch (ServiceException | ClassLoadingException e) {
                Dialogs.showErrorDialog(e, new DialogText("Ошибка экспорта", "Ошибка файла конфигураци",
                                                          "Ошибка экспорта схемы БД '" + comboBoxDB.getValue() +
                                                          "' из файла " + comboBoxDB.getValue().getConfigFileName()),
                                        logger);
            } catch (RuntimeException e) {
                Dialogs.showErrorDialog(e, new DialogText("Ошибка экспорта", "Ошибка выполнения экспорта схемы БД",
                                                          "Ошибка экспорта схемы БД '" + comboBoxDB.getValue() +
                                                          "' в файл " + textFieldFileName.getText()), logger);
            } catch (Exception e) {
                Dialogs.showErrorDialog(e, new DialogText("Ошибка экспорта", "Ошибка экспорта схемы БД",
                                                          "Ошибка экспорта схемы БД '" + comboBoxDB.getValue() +
                                                          "' в файл " + textFieldFileName.getText()), logger);
            } finally {
                logger.info("Закрыть соединение");
                if (metadata != null) {
                    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                    sessionFactory.close();
                }
            }

            okClicked = true;
            dialogStage.close();
        } else {
            Dialogs.showMessage(Alert.AlertType.WARNING, new DialogText("Ошибка файла", "Ошибка при проверке файла '" +
                                                                                        textFieldFileName.getText() +
                                                                                        "'",
                                                                        "Не верное имя или путь к файлу '" +
                                                                        textFieldFileName.getText() + "'"), logger);
        }

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
