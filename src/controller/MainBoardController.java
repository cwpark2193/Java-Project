package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainBoardController implements Initializable {
	@FXML private Button btnInfo;
	@FXML private Button btnResMag;
	@FXML private Button btnCareMag;
	@FXML private Button btnDiagnosis;
	@FXML private Button btnLogOut;
	@FXML private Button btnExit;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//1.개인정보 버튼 클릭시 메인정보창으로 이동
		btnInfo.setOnAction(e->handlerBtnInfoAction(e));
		//2.예약관리 버튼 클릭시 예약관리창으로 이동
		btnResMag.setOnAction(e->handlerBtnResMagAction(e));
		//3.진료기록 버튼 클릭시 진료기록창으로 이동
		btnCareMag.setOnAction(e->handlerBtnCareMagAction(e));
		//4.증명서 발급 버튼 클릭시 발급창으로 이동
		btnDiagnosis.setOnAction(e->handlerBtnDiagnosisAction(e));
		//6.로그아웃 버튼 클릭시 로그인창으로 이동
		btnLogOut.setOnAction(e->handlerBtnLogOutAction(e));
		//7.종료 버튼 클릭시 전체 창 종료
		btnExit.setOnAction(e->handlerBtnExitAction(e));
	}
	//1.개인정보 버튼 클릭시 메인정보창으로 이동
	public void handlerBtnInfoAction(ActionEvent e) {
		try {
			Parent personView=FXMLLoader.load(getClass().getResource("/view/personidboard.fxml"));
			PMClass.callStage(personView, "개인정보 창", btnInfo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//2.예약관리 버튼 클릭시 예약관리창으로 이동
	public void handlerBtnResMagAction(ActionEvent e) {
		try {
			Parent resmainView=FXMLLoader.load(getClass().getResource("/view/reserv_manage.fxml"));
			PMClass.callStage(resmainView, "예약관리 창", btnResMag);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//3.진료기록 버튼 클릭시 진료기록창으로 이동
	public void handlerBtnCareMagAction(ActionEvent e) {
		try {
			Parent careManageView=FXMLLoader.load(getClass().getResource("/view/care_management_main.fxml"));
			PMClass.callStage(careManageView, "진료 기록창", btnCareMag);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//4.증명서 발급 버튼 클릭시 발급창으로 이동
	public void handlerBtnDiagnosisAction(ActionEvent e) {
		try {	
			Parent DiagView=FXMLLoader.load(getClass().getResource("/view/diagnosis.fxml"));
			PMClass.callStage(DiagView, "진단서 발급창", btnDiagnosis);
		}catch(Exception e1) {
			PMClass.alertDisplay(1,"메인 불러오기 실패","메인창 불러오기 오류",e1.toString()+e1.getMessage());
		}
	}
	//6.로그아웃 버튼 클릭시 로그인창으로 이동
	public void handlerBtnLogOutAction(ActionEvent e) {
		try {
			Parent loginView=FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			PMClass.callStage(loginView, "로그인 창",btnLogOut);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//7.종료 버튼 클릭시 전체 창 종료
	public void handlerBtnExitAction(ActionEvent e) {
		Platform.exit();
	}
	
}
