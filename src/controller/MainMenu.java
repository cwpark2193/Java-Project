package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainMenu implements Initializable{
	
	// SelectMain.fxml 화면 출력
	// Login. FXML 변수 입력.
	@FXML private Button btnMainLogin; // 로그인
	@FXML private Button btnMainExit; // 종료
	@FXML private PasswordField MainPassword; // 패스워드
	@FXML private TextField MainTextField; // 아이디.
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 버튼 확인 이벤트
		btnMainLogin.setOnAction(e->{handlerBtnMainLoginAction(e);});
		btnMainExit.setOnAction(e->{handlerBtnMainExit(e);});
//		MainTextField.setText("Test");
//		MainPassword.setText("1");
	}
	public void handlerBtnMainLoginAction(ActionEvent e) {
		// 아이디 , 패스워드가 입력 안되었을때 경고창 출력하기.
		if(MainTextField.getText().equals("cwpark2193") && MainPassword.getText().equals("qkrwogns1234")) {
			try {	
				Parent mainView=FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
				PMClass.callStage(mainView, "관리자 메인창", btnMainLogin);
			}catch(Exception e1) {
				PMClass.alertDisplay(1,"메인 불러오기 실패","메인창 불러오기 오류",e1.toString()+e1.getMessage());
			}
		// 올바르게 입력했을경우.
		}else if (MainTextField.getText().equals("tjdals1234") && MainPassword.getText().equals("rlatjdals5678")) {
			try {	
				Parent mainView=FXMLLoader.load(getClass().getResource("/view/SelectMain.fxml"));
				PMClass.callStage(mainView, "사용자 메뉴", btnMainLogin);
			}catch(Exception e1) {
				PMClass.alertDisplay(1,"사용자 창 불러오기 실패","사용자 창 불러오기 오류",e1.toString()+e1.getMessage());
			}
		}else {
			PMClass.alertDisplay(1,"로그인 오류","아이디와 패스워드가 일치하지 않습니다.","재 확인 바랍니다.");
		}
	}
	public void handlerBtnMainExit(ActionEvent e) {
		((Stage) btnMainExit.getScene().getWindow()).close();
	}
	
}
