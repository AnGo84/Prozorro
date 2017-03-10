package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import sample.prozorro.Common;
import sample.prozorro.Dialogs;
import sample.prozorro.Prozorro;
import sample.prozorro.pages.PageContent;
import sample.prozorro.pages.PageElement;
import sample.prozorro.sql.SQLConnection;
import sample.prozorro.tenders.Tender;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static sample.prozorro.Prozorro.*;

public class Controller {

    private Properties properties;
    private Connection dbConnection;

    private Date dateFrom;
    private Date dateTill;
    private String approximatelyTime;

    private Boolean urlConnection = false;

    int allTenders;

    private List<PageContent> pageContentList = null;
    @FXML
    private Button buttonGetPages;
    @FXML
    private Button buttonGetTenders;
    @FXML
    private TextField tFieldXMLFolder;
    @FXML
    private TextArea textArea;
    @FXML
    private DatePicker datePickerFrom;
    @FXML
    private DatePicker datePickerTill;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private MenuItem menuItemParseList;
    @FXML
    private MenuItem menuItemParseTender;

    @FXML
    private void initialize() {
        buttonGetPages.setTooltip(new Tooltip("Выбрать страницы за указанный период"));
        buttonGetTenders.setTooltip(new Tooltip("Выбрать тендеры с отбранных страниц"));
        textArea.appendText("Start" + "\n");
        datePickerFrom.setValue(LocalDate.now());
        datePickerTill.setValue(LocalDate.now());
        try {
//            properties = getConnectionProperties("resources/database.properties");
            properties = getConnectionProperties("src/sample/resources/database.properties");
            textArea.appendText("Открыт файл настроек: " + Prozorro.getPropertyText(properties) + "\n");
        } catch (IOException e) {
//            showMessage(Alert.AlertType.ERROR, "Ошибка", "Ошибка открытия файла \'/resources/database.properties\'!", e.getMessage());
            textArea.appendText("Ошибка открытия файла настроек \'/resources/database.properties\' или файл не найден!" + "\n");
            textArea.appendText(e.getMessage() + "\n");

        }
        try {
            dbConnection = SQLConnection.getDBConnection(properties);
        } catch (ClassNotFoundException e) {
            textArea.appendText(e.getMessage() + "\n");
        } catch (SQLException e) {
            textArea.appendText(e.getMessage() + "\n");
        }
        textArea.appendText("Подключено к БД " + "\n");

        try {
            Prozorro.parseUrl(new URL(Prozorro.START_PAGE));
            urlConnection = true;

        } catch (MalformedURLException e) {
            textArea.appendText(e.getMessage() + "\n");
            Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка подключения по URL", e.getMessage());
        } catch (IOException e) {
            textArea.appendText(e.getMessage() + "\n");
            Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка подключения по URL", e.getMessage());
        }

    }

    public void onChooseXMLFolder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =
                directoryChooser.showDialog(tFieldXMLFolder.getScene().getWindow());

        if (selectedDirectory != null) {
            tFieldXMLFolder.setText(selectedDirectory.getAbsolutePath());
            textArea.appendText("Установлен каталог для выгрузки XML-файлов: " + tFieldXMLFolder.getText() + "\n");
        } else {
//            tFieldXMLFolder.setText("No Directory selected");
        }
    }

    public void onChoosePages(ActionEvent actionEvent) {
        if (checkInputDate()) {
            buttonGetTenders.setDisable(true);
            dateFrom = Date.from(datePickerFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dateTill = Date.from(datePickerTill.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            textArea.appendText("Отбираются тендеры за период с " + simpleDateShotFormat.format(dateFrom) + " по " + simpleDateShotFormat.format(dateTill) + "\n");

            if (dbConnection != null || (new File(tFieldXMLFolder.getText()).exists())) {
                Task task = new Task() {
                    @Override
                    protected Integer call() throws Exception {
//                    System.out.println(buttonGetPages.getScene().getClass().toString());
                        buttonGetPages.getParent().getParent().setDisable(true);
                        buttonGetPages.getParent().getScene().setCursor(Cursor.WAIT); //Change cursor to wait style
                        try {
                            pageContentList = Prozorro.getPagesList(dateFrom, dateTill);
                            textArea.appendText("Найдено страниц с тендерами: " + pageContentList.size() + "\n");
                            approximatelyTime = Prozorro.getTextTime(Prozorro.getAvgParsingSize(pageContentList.get(0), dateTill) * pageContentList.size());
                            buttonGetTenders.setDisable(false);
                        } catch (java.text.ParseException e) {
                            textArea.appendText(e.getMessage() + "\n");
                            Dialogs.showErrorDialog(e, "Exception Dialog", "Look, an Exception Dialog", e.getMessage());
                        } catch (IOException e) {
                            textArea.appendText(e.getMessage() + "\n");
                            Dialogs.showErrorDialog(e, "Exception Dialog", "Look, an Exception Dialog", e.getMessage());
                        }
                        buttonGetPages.getParent().getScene().setCursor(Cursor.DEFAULT); //Change cursor to default style
                        buttonGetPages.getParent().getParent().setDisable(false);
                        return pageContentList.size();
                    }
                };
                Thread th = new Thread(task);
//            th.setDaemon(true);
                th.start();
            } else {
                textArea.appendText("Нет подключения к БД и не выбран каталог. Зачем работать?");
                Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет подключения к БД и не выбран каталог!", "Нет подключения к БД и не выбран каталог. Зачем работать!");
            }
        }
    }

    private Boolean checkInputDate() {
        if (datePickerFrom.getValue() == null) {
            Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Дата \"С\" не установлена!", "Дата \"С\" не может быть пустой!");
            datePickerFrom.requestFocus();
            return false;
        }
        if (datePickerTill.getValue() == null) {
            Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Дата \"ПО\" не установлена!", "Дата \"ПО\" не может быть пустой!");
            datePickerTill.requestFocus();
            return false;
        }
        if (datePickerFrom.getValue().compareTo(datePickerTill.getValue()) > 0) {
            Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Дата \"С\" больше даты \"ПО\"!", "Дата \"С\" не может быть больше даты \"ПО\"!");
            datePickerTill.requestFocus();
            return false;
        }
        return true;
    }



    public void onGetTenders(ActionEvent actionEvent) {
        if (pageContentList.size() > 0) {
            if (Dialogs.showConfirmDialog("Подтвердите действие", "Время выполнения примерно " + approximatelyTime, "Время выполнения примерно " + approximatelyTime + ". Продолжить?")) {

                if (dbConnection != null) {
                    try {
                        SQLConnection.cleanTables(dbConnection);
                        textArea.appendText("Таблицы очищены" + "\n");
                    } catch (SQLException e) {
                        textArea.appendText(e.getMessage() + "\n");
                    }
                }

                Thread th = new Thread(getTendersTask(pageContentList));
//            th.setDaemon(true);
                th.start();

            }
        } else {
            Dialogs.showMessage(Alert.AlertType.WARNING, "Предупреждение", "Нет страниц для разбора!", "За указанный период нет данных для разбора.");
        }
    }

    private Task getTendersTask(List<PageContent> pageContents) {
        progressBar.setProgress(0);
        progressIndicator.setProgress(0);
        Task task = new Task() {
            @Override
            protected Integer call() throws Exception {
//                    System.out.println(buttonGetPages.getScene().getClass().toString());
                int pageCount = 0;
                int findTenders = 0;
                buttonGetTenders.getParent().getParent().setDisable(true);
                buttonGetTenders.getParent().getScene().setCursor(Cursor.WAIT); //Change cursor to wait style
                for (PageContent pageContent : pageContents) {
                    List<Tender> tenderList = null;
                    try {
                        tenderList = Prozorro.getTendersFromPage(pageContent, dateTill);
                    } catch (IOException e) {
                        textArea.appendText(e.getMessage() + "\n");
                    } catch (ParseException e) {
                        textArea.appendText(e.getMessage() + "\n");
                    }
                    pageCount++;
                    findTenders += tenderList.size();
                    if (dbConnection != null) {
                        try {
                            SQLConnection.insertTenders(dbConnection, tenderList);
//                            System.out.println("Added " + tenderList.size() + " new Tenders to DB");
                            textArea.appendText("Добавлено " + tenderList.size() + " новых тендеров в базу. Страница: " + pageContent.getPageHeader() + "\n");
                        } catch (SQLException e) {
                            textArea.appendText(e.getMessage() + "\n");
                        }
                    }
                    if (new File(tFieldXMLFolder.getText()).exists()) {
                        try {
                            Prozorro.writeToXMLFile(tenderList, new File(tFieldXMLFolder.getText() + "\\page_" + pageCount + ".xml"));
                            textArea.appendText("Создан файл: " + tFieldXMLFolder.getText() + "\\page_" + pageCount + ".xml" + "\n");
                        } catch (FileNotFoundException e) {
                            textArea.appendText(e.getMessage() + "\n");
                        }
                    }
                    progressBar.setProgress(Double.valueOf(pageCount) / pageContents.size());
                    progressIndicator.setProgress(Double.valueOf(pageCount) / pageContents.size());
                }
                buttonGetTenders.getParent().getScene().setCursor(Cursor.DEFAULT); //Change cursor to default style
                buttonGetTenders.getParent().getParent().setDisable(false);
                allTenders = findTenders;
                textArea.appendText("Найдено " + allTenders + " тендеров " + "\n");
                return findTenders;
            }
        };
        return task;
    }

    public void onParsePage(ActionEvent actionEvent) {
        if (urlConnection) {
            String inputText = Dialogs.showInputDialog("Разобрать страницу по URl", "Укажите страницу для разбора", "Страница:");
            textArea.appendText("Страница для разбора: " + inputText + "\n");
            if (!inputText.isEmpty() && inputText != null) {

                buttonGetTenders.getParent().getScene().setCursor(Cursor.WAIT);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PageContent pageContent = getPageContent(new URL(inputText));
                            for (PageElement pageElement : pageContent.getPageElementList()) {
                                textArea.appendText(pageElement.toString() + "\n");
                            }
                        } catch (org.json.simple.parser.ParseException e) {
                            textArea.appendText(e.getMessage() + "\n");
                            Dialogs.showErrorDialog(e, "Exception Dialog", "Разбора страницы по URL", e.getMessage());
                        } catch (IOException e) {
                            textArea.appendText(e.getMessage() + "\n");
                            Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка подключения по URL", e.getMessage());
                        }
                        buttonGetTenders.getParent().getScene().setCursor(Cursor.DEFAULT);
                    }
                });

//                try {
//                    PageContent pageContent = getPageContent(new URL(inputText));
//                    for (PageElement pageElement : pageContent.getPageElementList()) {
//                        textArea.appendText(pageElement.toString() + "\n");
//                    }
//                } catch (org.json.simple.parser.ParseException e) {
//                    textArea.appendText(e.getMessage() + "\n");
//                    showErrorDialog(e, "Exception Dialog", "Разбора страницы по URL", e.getMessage());
//                } catch (IOException e) {
//                    textArea.appendText(e.getMessage() + "\n");
//                    showErrorDialog(e, "Exception Dialog", "Ошибка подключения по URL", e.getMessage());
//                }
            }

        } else
            Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка ", "Ошибка подключенияпо ссылке", "Ошибка доступа или ошибка данных с сайта Прозоро");
    }

    public void onClean(ActionEvent actionEvent) {
        if (Dialogs.showConfirmDialog("Очистить логи", "Высобираетесь очистить текст с логими", "Весь текст будет утерян. Продолжить?")) {
            textArea.clear();
        }
    }

    public void onClose(ActionEvent actionEvent) {
        if (Dialogs.showConfirmDialog("Закрытие приложения", "Приложение будет закрыто", "Вы точно хотите закрыть приложение?"))
            Platform.exit();
    }

    public void onParseTender(ActionEvent actionEvent) {
        if (urlConnection) {
            String inputText = Dialogs.showInputDialog("Разобрать тендер по ID", "Укажите ID тендера для разбора", "Тендер:");
            textArea.appendText("Тендер для разбора: " + inputText + "\n");
            if (!inputText.isEmpty() && inputText != null) {
                buttonGetTenders.getParent().getScene().setCursor(Cursor.WAIT);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Tender tender = getTender(inputText);
                            textArea.appendText(tender.toString() + "\n");
                        } catch (IOException e) {
                            textArea.appendText(e.getMessage() + "\n");
                            Dialogs.showErrorDialog(e, "Exception Dialog", "Ошибка доступа по ID", e.getMessage());
                        }
                        buttonGetTenders.getParent().getScene().setCursor(Cursor.DEFAULT);
                    }
                });
            }

//            try {
//                Tender tender = getTender(inputText);
//                textArea.appendText(tender.toString() + "\n");
//            } catch (IOException e) {
//                textArea.appendText(e.getMessage() + "\n");
//                showErrorDialog(e, "Exception Dialog", "Ошибка доступа по ID", e.getMessage());
//            }
        } else
            Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка ", "Ошибка подключенияпо ссылке", "Ошибка доступа или ошибка данных с сайта Прозоро");

    }

    public void onSaveLogToFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(buttonGetPages.getScene().getWindow());

        if (file != null) {
            textArea.appendText("Выбран  файл для сохранения журнала: " + file.getAbsoluteFile() + "\n");
            try {
                Common.SaveToFile(textArea.getText(), file);
            } catch (IOException e) {
                Dialogs.showMessage(Alert.AlertType.WARNING, "Ошибка сохранения", "Ошибка при попытке сохранить файл " + file.getAbsoluteFile(), e.getMessage());
            }
        }
    }

}
