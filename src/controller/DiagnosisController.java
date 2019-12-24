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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DiagnosisController implements Initializable {
	@FXML private TextField tfPatName;
	@FXML private TextField tfPatGen;
	@FXML private TextField tfPatAge;
	@FXML private TextField tfPatSsn;
	@FXML private TextField tfPatAdd;
	@FXML private TextField tfPatPhone;
	@FXML private TextField tfResDate;
	@FXML private TextField tfPuDate;
	@FXML private TextField tfHosname;
	@FXML private TextField tfHosAdd;
	@FXML private TextField tfHosPhone;
	@FXML private TextField tfDocLc;
	@FXML private TextField tfDocName;
	@FXML private TextArea taRemark;
	@FXML private TextArea taPurpose;
	@FXML private TextArea taPresc;
	@FXML private TextArea taReson;
	@FXML private Button btnPrint;
	@FXML private Button btnClear;
	@FXML private Button btnClose;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//인쇄버튼
		btnPrint.setOnAction(e->handlerBtnPrintAction(e));
		//초기화버튼
		btnClear.setOnAction(e->handlerBtnClearAction(e));
		//종료버튼
		btnClose.setOnAction(e->handlerBtnCloseAction(e));
	}
	//인쇄버튼
	public void handlerBtnPrintAction(ActionEvent e) {
		try {
			PMClass.alertDisplay(5, "인쇄 창", "잠시만 기다려 주십시오.", "인쇄를 준비중입니다...");
			
			PMClass.alertDisplay(5, "출력 완료", "출력되었습니다.", "인쇄물을 가져가십시오.");
			
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "관리자 메인창", btnClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}
	//초기화버튼
	public void handlerBtnClearAction(ActionEvent e) {
		fieldClearInit();
	}
	//종료버튼
	public void handlerBtnCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "관리자 메인창", btnClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}
	//필드 초기화
	public void fieldClearInit() {
		tfPatName.clear(); tfPatGen.clear(); tfPatAge.clear(); tfPatSsn.clear(); tfPatAdd.clear(); tfPatPhone.clear();
		tfResDate.clear(); tfPuDate.clear(); tfHosname.clear(); tfHosAdd.clear(); tfHosPhone.clear(); tfDocLc.clear();
		tfDocName.clear(); taRemark.clear(); taPurpose.clear(); taPresc.clear(); taReson.clear();	
	}

}
