package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//1. 진료 조회를 할수있는 클래스.
public class LookUp implements Initializable {
	@FXML private ListView listView2; // DB에 등록받는 리스트뷰.
	@FXML private DatePicker dtePick2; // 날짜
	@FXML private Button btnExit; // 종료하기
	
	@FXML private TextField txtMedical; //  진료과 
	@FXML private TextField txtDay; // 날짜
	@FXML private TextField txtDtName; // 의사 이름
	@FXML private TextField txtPtName; // 환자 이름
	@FXML private TextField txtMoney; // 진료금
	
	@FXML private TextArea txtBackdown; //진료내역
	@FXML private TextArea txtPre; // 처방내역 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnExit.setOnAction(e->{handlerBtnExitAction(e);});
	}
	// 조회하기 눌렀을때 사용자의 정보가 일치한지 확인한다.
	public void handlerBtnJoinAction(ActionEvent e) {
		Alert alert = new Alert(AlertType.WARNING);
		
			txtMedical.setDisable(true);
			
			txtDay.setDisable(true);
			txtDtName.setDisable(true);
		
			txtPtName.setDisable(true);
		
			txtMoney.setDisable(true);
			txtBackdown.setDisable(true);
			txtPre.setDisable(true);
	}
	public void handlerBtnExitAction(ActionEvent e) {
		((Stage)btnExit.getScene().getWindow()).close();
	}
	
}
