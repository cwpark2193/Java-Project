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

// 글쓰기 눌렀을때 진행하는 컨트롤러
public class NoticeBoardContentsController implements Initializable {

	@FXML
	private Button btnClose; // 해당 스테이지 종료.
	@FXML
	private Button btnSave; // 등록
	@FXML
	private ComboBox<String> Word; // 여기안에 6개 항목을 넣어야함.
	@FXML
	private TextArea AreaView; // 내용
	@FXML
	private RadioButton rdoOpen; // 공개여부 OK
	@FXML
	private RadioButton rdoOFF; // 공개여부 OFF
	@FXML
	private TextField txtHuman; // 작성자.
	@FXML
	private TextField txtTitle; // 제목
	@FXML
	private PasswordField txtPassword;

	ObservableList<String> list = FXCollections.observableArrayList("감자", "고구마", "정구지", "찌짐", "커피", "우유");

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
	// 1. 공개여부 버튼을 눌렀을시 비밀번호를 비활성화 시킨다.
	// 버튼 하나 클릭후 다음 버튼 넘어갔을떄 전에 클릭하던 버튼 값이 사라지게 할것.

	public void handlerBtnSaveAction(ActionEvent e)  {
		Alert alert = new Alert(AlertType.WARNING);
		try {
			Parent ListRoot = FXMLLoader.load(getClass().getResource("/view/noticeboard_contents.fxml"));
			Stage stageDialog=new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnSave.getScene().getWindow());
			stageDialog.setTitle(" ");
			TextField ListOne = (TextField) ListRoot.lookup("#txtTitle");
			alert.setTitle("확인 창");
			alert.setHeaderText("Table View Input");
			alert.setContentText("성공.");
			alert.showAndWait();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void handlerRdoOpenAction(ActionEvent e) {
		txtPassword.setDisable(true);
	}

	// 2. 공개여부 비활성시 비밀번호를 활성화 시킨다.
	public void handlerRdoOffAction(ActionEvent e) {
		txtPassword.setDisable(false);
	}

}
