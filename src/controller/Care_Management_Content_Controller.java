package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Care_Management_MainVO;
import model.DoctorVO;

public class Care_Management_Content_Controller implements Initializable {
	
	@FXML TextField tfDocName;
	@FXML TextField tfDocPhone;
	@FXML TextField tfPatName;
	@FXML TextField tfCarePay;
	@FXML TextField tfPatPhone;
	@FXML TextArea taCareConts;
	@FXML TextArea taPresConts;
	@FXML Button btnSave;
	@FXML Button btnClear;
	@FXML Button btnCareClose;
	@FXML DatePicker dpCareDate;
	@FXML ComboBox<String> cbCareTime;
	public int state=0;
	public int selectId=0;
	public Care_Management_MainVO cmmVO=null;
	private boolean editDelete = false;
	private boolean editClose = false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 콤보박스 세팅
		cbCareTime.setItems(FXCollections.observableArrayList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"));
		// 등록 버튼
		btnSave.setOnAction(e -> handlerBtnSaveAction(e));
		// 초기화버튼 액션
		btnClear.setOnAction(e -> handlerBtnClearAction(e));
		// 종료 버튼 액션
		btnCareClose.setOnAction(e -> handlerBtnCareCloseAction(e));
		// 정보 메인창에서 수정버튼 눌러졌을 때
		try {
			if(state == 1) {
				tfDocName.setText(cmmVO.getDoctorName());
				tfDocPhone.setText(cmmVO.getDoctorPhone());
				tfPatName.setText(cmmVO.getPatientName());
				tfPatPhone.setPromptText(cmmVO.getPatientPhone());
				tfCarePay.setText(String.valueOf(cmmVO.getCarePay()));
				dpCareDate.setPromptText(cmmVO.getReservCareDate());
				cbCareTime.setPromptText(cmmVO.getReservCaretime());
				taCareConts.setText(cmmVO.getCareHistory());
				taPresConts.setText(cmmVO.getPrescription());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	//초기화 버튼
	public void handlerBtnClearAction(ActionEvent e) {
		tfDocName.clear(); tfDocPhone.clear(); tfPatName.clear(); tfPatPhone.clear();
		dpCareDate.getEditor().clear(); cbCareTime.getSelectionModel().clearSelection(); taCareConts.clear();
		taPresConts.clear(); tfCarePay.clear();
	}
	// 등록 버튼
	public void handlerBtnSaveAction(ActionEvent e) {
		try {
			if(state==1) {
				Care_Management_MainVO careVO = new Care_Management_MainVO(cmmVO.getCareNumber(),tfDocName.getText(),tfDocPhone.getText(),tfPatName.getText(),
						tfPatPhone.getText(),String.valueOf(dpCareDate.getValue()),cbCareTime.getValue(),taCareConts.getText(),taPresConts.getText(),
						Integer.parseInt(tfCarePay.getText()));
				Care_ManagementDAO CMDAO = new Care_ManagementDAO();
				CMDAO.getCmmUpdate(careVO, cmmVO.getCareNumber());
			}else {
				Care_Management_MainVO careVO = new Care_Management_MainVO(tfDocName.getText(),tfDocPhone.getText(),tfPatName.getText(),
						tfPatPhone.getText(),String.valueOf(dpCareDate.getValue()),cbCareTime.getValue(),taCareConts.getText(),taPresConts.getText(),
						Integer.parseInt(tfCarePay.getText()));
				Care_ManagementDAO CMDAO = new Care_ManagementDAO();
				CMDAO.getCareRegiste(careVO);		
			}
			PMClass.alertDisplay(1, "등록 성공", "목록에 등록 완료", "수고하셨습니다");	
		} catch (Exception e1) {
//			PMClass.alertDisplay(1, "등록 실패", "다시 한번 시도하세요", e.toString());
			e1.printStackTrace();
			return;
		}
		
	}
	// 종료 버튼 액션
	public void handlerBtnCareCloseAction(ActionEvent e) {
		if(editClose==true) {
			((Stage)btnCareClose.getScene().getWindow()).close();
			editClose=false;
		}else {
			((Stage)btnCareClose.getScene().getWindow()).close();	
			try {
				Parent mainView = FXMLLoader.load(getClass().getResource("/view/care_management_main.fxml"));
				PMClass.callStage(mainView, "관리자 메인창", btnCareClose);
			} catch (Exception e1) {
				PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
			}
		}
	}
	
}
