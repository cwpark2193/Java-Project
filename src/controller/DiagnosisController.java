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
		//�μ��ư
		btnPrint.setOnAction(e->handlerBtnPrintAction(e));
		//�ʱ�ȭ��ư
		btnClear.setOnAction(e->handlerBtnClearAction(e));
		//�����ư
		btnClose.setOnAction(e->handlerBtnCloseAction(e));
	}
	//�μ��ư
	public void handlerBtnPrintAction(ActionEvent e) {
		try {
			PMClass.alertDisplay(5, "�μ� â", "��ø� ��ٷ� �ֽʽÿ�.", "�μ⸦ �غ����Դϴ�...");
			
			PMClass.alertDisplay(5, "��� �Ϸ�", "��µǾ����ϴ�.", "�μ⹰�� �������ʽÿ�.");
			
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "������ ����â", btnClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� �ҷ����� ����", "����â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}
	//�ʱ�ȭ��ư
	public void handlerBtnClearAction(ActionEvent e) {
		fieldClearInit();
	}
	//�����ư
	public void handlerBtnCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "������ ����â", btnClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� �ҷ����� ����", "����â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}
	//�ʵ� �ʱ�ȭ
	public void fieldClearInit() {
		tfPatName.clear(); tfPatGen.clear(); tfPatAge.clear(); tfPatSsn.clear(); tfPatAdd.clear(); tfPatPhone.clear();
		tfResDate.clear(); tfPuDate.clear(); tfHosname.clear(); tfHosAdd.clear(); tfHosPhone.clear(); tfDocLc.clear();
		tfDocName.clear(); taRemark.clear(); taPurpose.clear(); taPresc.clear(); taReson.clear();	
	}

}
