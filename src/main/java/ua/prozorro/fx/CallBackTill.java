package ua.prozorro.fx;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class CallBackTill implements Callback<DatePicker, DateCell> {
	private DatePicker datePickerFrom;
	
	public CallBackTill(DatePicker datePickerFrom) {
		this.datePickerFrom = datePickerFrom;
	}
	
	@Override
	public DateCell call(DatePicker param) {
		return new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item.isBefore(datePickerFrom.getValue().plusDays(0)) || item.isAfter(LocalDate.now())) {
					setDisable(true);
					setStyle("-fx-background-color: #e0a8b0;");
				}
			}
		};
	}
}
