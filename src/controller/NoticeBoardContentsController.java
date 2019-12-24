package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// �۾��� �������� �����ϴ� ��Ʈ�ѷ�
public class NoticeBoardContentsController implements Initializable {

	@FXML
	private Button btnClose; // �ش� �������� ����.
	@FXML
	private Button btnSave; // ���
	@FXML
	private ComboBox<String> Word; // ����ȿ� 6�� �׸��� �־����.
	@FXML
	private TextArea AreaView; // ����
	@FXML
	private RadioButton rdoOpen; // �������� OK
	@FXML
	private RadioButton rdoOFF; // �������� OFF
	@FXML
	private TextField txtHuman; // �ۼ���.
	@FXML
	private TextField txtTitle; // ����
	@FXML
	private PasswordField txtPassword;

	ObservableList<String> list = FXCollections.observableArrayList("����", "����", "������", "����", "Ŀ��", "����");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Word.setItems(list);
		btnSave.setOnAction(e -> {
			handlerBtnSaveAction(e);
		});
		rdoOpen.setOnAction(e -> {
			handlerRdoOpenAction(e);
		});
		rdoOFF.setOnAction(e -> {
			handlerRdoOffAction(e);
		});
	}
	// 1. �������� ��ư�� �������� ��й�ȣ�� ��Ȱ��ȭ ��Ų��.
	// ��ư �ϳ� Ŭ���� ���� ��ư �Ѿ���� ���� Ŭ���ϴ� ��ư ���� ������� �Ұ�.

	public void handlerBtnSaveAction(ActionEvent e)  {
		Alert alert = new Alert(AlertType.WARNING);
		try {
			Parent ListRoot = FXMLLoader.load(getClass().getResource("/view/noticeboard_contents.fxml"));
			Stage stageDialog=new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnSave.getScene().getWindow());
			stageDialog.setTitle(" ");
			TextField ListOne = (TextField) ListRoot.lookup("#txtTitle");
			alert.setTitle("Ȯ�� â");
			alert.setHeaderText("Table View Input");
			alert.setContentText("����.");
			alert.showAndWait();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void handlerRdoOpenAction(ActionEvent e) {
		txtPassword.setDisable(true);
	}

	// 2. �������� ��Ȱ���� ��й�ȣ�� Ȱ��ȭ ��Ų��.
	public void handlerRdoOffAction(ActionEvent e) {
		txtPassword.setDisable(false);
	}

}
