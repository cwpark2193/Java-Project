package controller;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class DatePickerListener {
	private DatePicker dpCheckIn;

	public DatePickerListener(DatePicker dpCheckIn) {
		this.dpCheckIn = dpCheckIn;
	}
	public Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		@Override
		public DateCell call(final DatePicker datePicker) {
			return new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					
					if (datePicker.getId().equals(dpCheckIn.getId())) {
						if (item.isBefore(LocalDate.now())) {

							setDisable(true);
							setStyle("-fx-background-color: #d3d3d3;");
						}
					} else {
						
						if (item.isBefore(dpCheckIn.getValue().plusDays(1))) {
							setDisable(true);

							setStyle("-fx-background-color: #d3d3d3;");

						}
					}
				}
			};
		}
	};
}