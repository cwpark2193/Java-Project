package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.crypto.spec.IvParameterSpec;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Reservation implements Initializable {

	// 1. 온라인 진료예약 홈페이지 구현.
	// 2. 진료과 / 의료진 으로 예약
	// 3. 해당되는 진료과에 맞는 xx 교수 사진 출력 및 날짜/시간 선택 + 예약사유
	@FXML	private Button btnExit; // 나가기
	@FXML	private Button btnRes; // 예약하기.
	@FXML	private TextArea txtArea; // 텍스트 에어리어.
	@FXML	private DatePicker dtePick; // 날 ㅉ
	@FXML	private ComboBox<String> SelectDia; // 진료시간 선택
	@FXML	private ImageView imageChange; // 사진 ,
	@FXML	ListView<String> listView;
	ObservableList<String> list = FXCollections.observableArrayList("09:00", "10:00", "11:00", "13:00", "14:00",
			"15:00", "16:00", "17:00", "18:00");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRes.setOnAction(e -> {
			handlerBtnRes(e);
		});
		btnExit.setOnAction(e -> {
			handlerBtnExit(e);
		});
		SelectDia.setOnAction(e -> {
			handlerSelectDia(e);
		});
		SelectDia.setItems(list);
		// 화면을 클릭하면 해당된 정보를 찾아서 가져와서 리스트뷰에 넣어라.
		listViewInsert();
	}
	// 화면을 클릭하면 해당된 정보를 찾아서 가져와서 리스트뷰에 넣어라.
	public void listViewInsert() {
		ObservableList<String> lvData=FXCollections.observableArrayList();
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctorinfo.fxml"));
//		DoctorInfoController docInfoCont = loader.getController();
//		docInfoCont.doctorVO = selectDoctor.get(0);

		lvData.add("박지원"); lvData.add("김해찬"); lvData.add("이세림");lvData.add("정혜지");
		listView.setItems(lvData);
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				imageChange
			}
		});
	}

	// 종료하기 이벤트.
	public void handlerBtnExit(ActionEvent e) {
		((Stage) btnExit.getScene().getWindow()).close();
	}

	// 예약하기 이벤트
	public void handlerBtnRes(ActionEvent e) {
		PMClass.alertDisplay(5, "예약 완료", "예약이 완료되었습니다.", "해당 날짜에 꼭 와주시기 바랍니다.");
		txtArea.clear();
		dtePick.getEditor().clear();
		SelectDia.getSelectionModel().clearSelection();

	}

	// 리스트 이름 선택시 사진 불러오기
	public void handlerSelectDia(ActionEvent e) {
	}
}
