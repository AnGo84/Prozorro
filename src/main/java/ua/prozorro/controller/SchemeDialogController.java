package ua.prozorro.controller;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ua.prozorro.sql.HibernateDataBaseType;

public class SchemeDialogController {
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
	private void initialize() {
		comboBoxDB.getItems().setAll(HibernateDataBaseType.values());

		buttonOk.setTooltip(new Tooltip("Выполнить"));
		buttonCancel.setTooltip(new Tooltip("Отменить выгрузку"));
		buttonChooseFile.setTooltip(new Tooltip("Выбрать файл"));

		textFieldFileName.setText("");

		checkBoxSaveScheme.setSelected(false);

	}

	public void onButtonChooseFile(ActionEvent actionEvent) {
	}

	public void onButtonOk(ActionEvent actionEvent) {
	}

	public void onButtonCancel(ActionEvent actionEvent) {
	}
}
