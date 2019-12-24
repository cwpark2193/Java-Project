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
		//1.�������� ��ư Ŭ���� ��������â���� �̵�
		btnInfo.setOnAction(e->handlerBtnInfoAction(e));
		//2.������� ��ư Ŭ���� �������â���� �̵�
		btnResMag.setOnAction(e->handlerBtnResMagAction(e));
		//3.������ ��ư Ŭ���� ������â���� �̵�
		btnCareMag.setOnAction(e->handlerBtnCareMagAction(e));
		//4.���� �߱� ��ư Ŭ���� �߱�â���� �̵�
		btnDiagnosis.setOnAction(e->handlerBtnDiagnosisAction(e));
		//6.�α׾ƿ� ��ư Ŭ���� �α���â���� �̵�
		btnLogOut.setOnAction(e->handlerBtnLogOutAction(e));
		//7.���� ��ư Ŭ���� ��ü â ����
		btnExit.setOnAction(e->handlerBtnExitAction(e));
	}
	//1.�������� ��ư Ŭ���� ��������â���� �̵�
	public void handlerBtnInfoAction(ActionEvent e) {
		try {
			Parent personView=FXMLLoader.load(getClass().getResource("/view/personidboard.fxml"));
			PMClass.callStage(personView, "�������� â", btnInfo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//2.������� ��ư Ŭ���� �������â���� �̵�
	public void handlerBtnResMagAction(ActionEvent e) {
		try {
			Parent resmainView=FXMLLoader.load(getClass().getResource("/view/reserv_manage.fxml"));
			PMClass.callStage(resmainView, "������� â", btnResMag);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//3.������ ��ư Ŭ���� ������â���� �̵�
	public void handlerBtnCareMagAction(ActionEvent e) {
		try {
			Parent careManageView=FXMLLoader.load(getClass().getResource("/view/care_management_main.fxml"));
			PMClass.callStage(careManageView, "���� ���â", btnCareMag);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//4.���� �߱� ��ư Ŭ���� �߱�â���� �̵�
	public void handlerBtnDiagnosisAction(ActionEvent e) {
		try {	
			Parent DiagView=FXMLLoader.load(getClass().getResource("/view/diagnosis.fxml"));
			PMClass.callStage(DiagView, "���ܼ� �߱�â", btnDiagnosis);
		}catch(Exception e1) {
			PMClass.alertDisplay(1,"���� �ҷ����� ����","����â �ҷ����� ����",e1.toString()+e1.getMessage());
		}
	}
	//6.�α׾ƿ� ��ư Ŭ���� �α���â���� �̵�
	public void handlerBtnLogOutAction(ActionEvent e) {
		try {
			Parent loginView=FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			PMClass.callStage(loginView, "�α��� â",btnLogOut);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//7.���� ��ư Ŭ���� ��ü â ����
	public void handlerBtnExitAction(ActionEvent e) {
		Platform.exit();
	}
	
}
