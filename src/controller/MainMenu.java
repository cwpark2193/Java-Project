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
	
	// SelectMain.fxml ȭ�� ���
	// Login. FXML ���� �Է�.
	@FXML private Button btnMainLogin; // �α���
	@FXML private Button btnMainExit; // ����
	@FXML private PasswordField MainPassword; // �н�����
	@FXML private TextField MainTextField; // ���̵�.
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ��ư Ȯ�� �̺�Ʈ
		btnMainLogin.setOnAction(e->{handlerBtnMainLoginAction(e);});
		btnMainExit.setOnAction(e->{handlerBtnMainExit(e);});
//		MainTextField.setText("Test");
//		MainPassword.setText("1");
	}
	public void handlerBtnMainLoginAction(ActionEvent e) {
		// ���̵� , �н����尡 �Է� �ȵǾ����� ���â ����ϱ�.
		if(MainTextField.getText().equals("cwpark2193") && MainPassword.getText().equals("qkrwogns1234")) {
			try {	
				Parent mainView=FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
				PMClass.callStage(mainView, "������ ����â", btnMainLogin);
			}catch(Exception e1) {
				PMClass.alertDisplay(1,"���� �ҷ����� ����","����â �ҷ����� ����",e1.toString()+e1.getMessage());
			}
		// �ùٸ��� �Է��������.
		}else if (MainTextField.getText().equals("tjdals1234") && MainPassword.getText().equals("rlatjdals5678")) {
			try {	
				Parent mainView=FXMLLoader.load(getClass().getResource("/view/SelectMain.fxml"));
				PMClass.callStage(mainView, "����� �޴�", btnMainLogin);
			}catch(Exception e1) {
				PMClass.alertDisplay(1,"����� â �ҷ����� ����","����� â �ҷ����� ����",e1.toString()+e1.getMessage());
			}
		}else {
			PMClass.alertDisplay(1,"�α��� ����","���̵�� �н����尡 ��ġ���� �ʽ��ϴ�.","�� Ȯ�� �ٶ��ϴ�.");
		}
	}
	public void handlerBtnMainExit(ActionEvent e) {
		((Stage) btnMainExit.getScene().getWindow()).close();
	}
	
}
