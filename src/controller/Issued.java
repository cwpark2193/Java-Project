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

// �ǹ���� �纻�� ����ϴ� Ŭ����.
public class Issued implements Initializable {
	@FXML
	private TextField txtName; // �����ϴ»�� �̸�
	@FXML
	private TextField txtSSM; // �����ϴ� ��� �ֹε�Ϲ�ȣ
	@FXML
	private TextField txtAdd; // �����ϴ� ��� �ּ�
	@FXML
	private TextField txtName2; // ����
	@FXML
	private DatePicker dteToday; //  ���� ��¥.
	@FXML
	private TextField txtAdd2; // �������
	@FXML
	private TextArea txtMandate;// ó�泻��
	@FXML
	private TextArea txtReason; // ���
	@FXML
	private TextField txtRealtion; // �뵵
	@FXML
	private TextField txtToday; // ������

	@FXML
	private Button btnPrint;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnPrint.setOnAction(e -> {handlerBtnPrint(e);});}
	// �μ��ϱ� ��ư�� �������� �̺�Ʈ�� ó���Ѵ�.
	public void handlerBtnPrint(ActionEvent e) {
		Alert alert = new Alert(AlertType.WARNING);
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("�μ� â");
		dialog.setHeaderText("�μ⸦ �غ����Դϴ� !");
		dialog.setContentText("������ Ȯ���� ���� ������ ���� �Է����ּ��� :");
		dialog.showAndWait();
		if (dialog.getEditor().getText().equals("tjdals1234")) {
			alert.setTitle("Ȯ�� â");
			alert.setHeaderText("�ſ� Ȯ�� ��");
			alert.setContentText("����غ����Դϴ�.");
			alert.showAndWait();
		} else {
			alert.setTitle("��� â");
			alert.setHeaderText("�ſ� Ȯ�κҰ�");
			alert.setContentText("����� ����մϴ�.");
			alert.showAndWait();
		}
		if(dialog.getEditor().getText().equals("tjdals1234")) {
			txtName.clear(); // �����ϴ»�� �̸�
			txtSSM.clear(); // �����ϴ� ��� �ֹε�Ϲ�ȣ
			txtAdd.clear(); // �����ϴ� ��� �ּ�
			txtName2.clear(); // �޴»�� �̸�
			txtAdd2.clear(); // �޴»�� �ּ�
			txtRealtion.clear(); // ����
			dteToday.getEditor().clear();
			 txtMandate.clear();
			 txtReason.clear();
			 txtToday.clear();
			 
			alert.setHeaderText("����Ͽ����ϴ�.");
			alert.setContentText("Success");
			alert.showAndWait();
			  ((Stage)btnPrint.getScene().getWindow()).close();		
			
		}else {
			alert.setHeaderText("������ ����ġ �մϴ�.");
			alert.setContentText("�ٽ� �Է� ���ּ���.");
			alert.showAndWait();
			
		}

			

	}
}