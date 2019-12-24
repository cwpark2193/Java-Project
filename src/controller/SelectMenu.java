package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class SelectMenu implements Initializable {
	// 1. 메인 선택
	@FXML
	private Button btnReservation; // 진로예약. 10.15 40% 완료항ㅁ
	@FXML
	private Button btnApply; // 검진예약신청
	@FXML
	private Button btnLookUp; // 진료조회
	@FXML
	private Button btnIssued; // 발급신청
	@FXML
	private Button btnLogOut; // 로그아웃.
	
	@FXML private Button btnDelete;
	@FXML private Button btnRe;
	@FXML private Button btnNBClose;
	@FXML private Button btnStart;
	@FXML
	private RadioButton rdoDoctor; // 의사 로그인 
	@FXML
	private RadioButton rdoUser;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnReservation.setOnAction(e -> {handlerBtnReservation(e);});
		btnApply.setOnAction(e -> {handlerBtnApply(e);});
		btnLookUp.setOnAction(e -> {handlerBtnLookUp(e);});
		btnIssued.setOnAction(e -> {handlerBtnIssued(e);});
		btnLogOut.setOnAction(e->{handlerBtnLogoutAction(e);});
//		btnStart.setOnAction(e->{handlerBtnStart(e);});
	}
	
	

	// 온라인 진료예약 창을 띄운다.
	public void handlerBtnReservation(ActionEvent e) {
		Parent mainView=null;
		Stage mainStage=null;
		
		try {
			mainView=FXMLLoader.load(getClass().getResource("/view/Oneroot.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setScene(scene);
			mainStage.setTitle("온라인 진료예약");
			mainStage.setResizable(false);
			mainStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	// 발급신청
		public void handlerBtnIssued(ActionEvent e) {
			Parent mainView2 = null;
			Stage mainStage2=null;
			try {
				mainView2=FXMLLoader.load(getClass().getResource("/view/Issued.fxml"));
				Scene scene = new Scene(mainView2);
				mainStage2 = new Stage();
				mainStage2.setScene(scene);
				mainStage2.setTitle("의무 기록");
				mainStage2.setResizable(false);
				mainStage2.show();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	// 진료 조회
	public void handlerBtnLookUp(ActionEvent e) {
		Parent mainView3 = null;
		Stage mainStage3= null;
		try {
			mainView3=FXMLLoader.load(getClass().getResource("/view/LookUp.fxml"));
			Scene scene = new Scene(mainView3);
			mainStage3 = new Stage();
			mainStage3.setScene(scene);
			mainStage3.setTitle("의무기록");
			mainStage3.setResizable(false);
			mainStage3.show();
		}catch(Exception e3) {
			e3.printStackTrace();
		}
	}
	 // 건의 게시판 
	public void handlerBtnApply(ActionEvent e) {
		Parent mainView6 = null;
		Stage mainStage6= null;
		try {
			mainView6=FXMLLoader.load(getClass().getResource("/view/noticeboard_main.fxml"));
			Scene scene = new Scene(mainView6);
			mainStage6 = new Stage();
			mainStage6.setScene(scene);
			mainStage6.setTitle("의무기록");
			mainStage6.setResizable(false);
			mainStage6.show();
		}catch(Exception e3) {
			e3.printStackTrace();
		}

	}
	
	public void handlerBtnLogoutAction(ActionEvent e) {
		Parent mainView5 = null;
		Stage   mainStage5 = null;
		((Stage)btnLogOut.getScene().getWindow()).close();
		try {
			 mainView5=FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			 Scene scene = new Scene(mainView5);
			 mainStage5 = new Stage();
			 mainStage5.setScene(scene);
			 mainStage5.setResizable(true);
			 mainStage5.show();
		}catch(Exception e5) {
			e5.printStackTrace();
		}
		
	}
	

}
