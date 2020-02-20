package ua.prozorro.fx;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class CallBackFrom implements Callback<DatePicker, DateCell> {
	@Override
	public DateCell call(DatePicker param) {
		return new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item.isAfter(LocalDate.now())) {
					setDisable(true);
					setStyle("-fx-background-color: #e0a8b0;");
				}
			}
		};
	}
}
