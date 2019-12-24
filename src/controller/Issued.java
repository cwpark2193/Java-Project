package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

// 의무기록 사본을 출력하는 클래스.
public class Issued implements Initializable {
	@FXML
	private TextField txtName; // 위임하는사람 이름
	@FXML
	private TextField txtSSM; // 위임하는 사람 주민등록번호
	@FXML
	private TextField txtAdd; // 위임하는 사람 주소
	@FXML
	private TextField txtName2; // 나이
	@FXML
	private DatePicker dteToday; //  진단 날짜.
	@FXML
	private TextField txtAdd2; // 진료사유
	@FXML
	private TextArea txtMandate;// 처방내역
	@FXML
	private TextArea txtReason; // 비고
	@FXML
	private TextField txtRealtion; // 용도
	@FXML
	private TextField txtToday; // 발행일

	@FXML
	private Button btnPrint;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnPrint.setOnAction(e -> {handlerBtnPrint(e);});}
	// 인쇄하기 버튼을 눌렀을시 이벤트를 처리한다.
	public void handlerBtnPrint(ActionEvent e) {
		Alert alert = new Alert(AlertType.WARNING);
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("인쇄 창");
		dialog.setHeaderText("인쇄를 준비중입니다 !");
		dialog.setContentText("간단한 확인을 위해 위임자 명을 입력해주세요 :");
		dialog.showAndWait();
		if (dialog.getEditor().getText().equals("tjdals1234")) {
			alert.setTitle("확인 창");
			alert.setHeaderText("신원 확인 중");
			alert.setContentText("출력준비중입니다.");
			alert.showAndWait();
		} else {
			alert.setTitle("경고 창");
			alert.setHeaderText("신원 확인불가");
			alert.setContentText("출력을 취소합니다.");
			alert.showAndWait();
		}
		if(dialog.getEditor().getText().equals("tjdals1234")) {
			txtName.clear(); // 위임하는사람 이름
			txtSSM.clear(); // 위임하는 사람 주민등록번호
			txtAdd.clear(); // 위임하는 사람 주소
			txtName2.clear(); // 받는사람 이름
			txtAdd2.clear(); // 받는사람 주소
			txtRealtion.clear(); // 관계
			dteToday.getEditor().clear();
			 txtMandate.clear();
			 txtReason.clear();
			 txtToday.clear();
			 
			alert.setHeaderText("출력하였습니다.");
			alert.setContentText("Success");
			alert.showAndWait();
			  ((Stage)btnPrint.getScene().getWindow()).close();		
			
		}else {
			alert.setHeaderText("정보가 불일치 합니다.");
			alert.setContentText("다시 입력 해주세요.");
			alert.showAndWait();
			
		}

			

	}
}