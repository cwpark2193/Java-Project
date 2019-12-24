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

//1. ���� ��ȸ�� �Ҽ��ִ� Ŭ����.
public class LookUp implements Initializable {
	@FXML private ListView listView2; // DB�� ��Ϲ޴� ����Ʈ��.
	@FXML private DatePicker dtePick2; // ��¥
	@FXML private Button btnExit; // �����ϱ�
	
	@FXML private TextField txtMedical; //  ����� 
	@FXML private TextField txtDay; // ��¥
	@FXML private TextField txtDtName; // �ǻ� �̸�
	@FXML private TextField txtPtName; // ȯ�� �̸�
	@FXML private TextField txtMoney; // �����
	
	@FXML private TextArea txtBackdown; //���᳻��
	@FXML private TextArea txtPre; // ó�泻�� 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnExit.setOnAction(e->{handlerBtnExitAction(e);});
	}
	// ��ȸ�ϱ� �������� ������� ������ ��ġ���� Ȯ���Ѵ�.
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
