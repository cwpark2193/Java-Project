package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DoctorVO;
import model.noticeBoardVO;


// 건의게시판 , 칭찬게시판
public class NociteBoardMainController implements Initializable {
	@FXML
	private Button btnDelete; // 삭제하기
//	@FXML
//	private Button btnReset; // 새로고침
//	@FXML
//	private Button btnModify; // 수정하기
	@FXML
	private Button btnFinal; // 작성하기
	@FXML
	private RadioButton rdoOpen; // 공개
	@FXML
	private RadioButton rdoOFF; // 비공개
	@FXML
	private TextArea txtArea; // 내용적는곳
	@FXML
	private TextField txtHuman; // 작성자
	@FXML
	private TextField txtTitle; // 제목
	@FXML
	private PasswordField txtPassword; // 비밀번호
	@FXML
	private ComboBox<String> comboBox; // 건의게시판 칭찬게시판 Clear
	// 테이블뷰에 보여주기 위한 데이터
	@FXML
	private TableView<HospitalNo1> tableView;
	ObservableList<HospitalNo1> Tbldata;
//===================================	
	//테이블뷰를 선택했을때 위치값과 객체값을 저장할수 있는 변수 선언.
	private  int selected;
	private ObservableList<HospitalNo1> selected2;
	
	private boolean edit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ComboboxSetting();
		TableViewSetting();
		ResetContents();
		//테이블 뷰 클릭시
		tableView.setOnMousePressed(e -> {BtnTableViewPrint(e);});
		// 작성하기 버튼
		btnFinal.setOnAction(e -> {handlerBtnFinalAction(e);});
		// 삭제하기 버튼
		btnDelete.setOnAction(e -> {handlerBtnDeleteAction(e);});
//		 btnModify.setOnAction(e->{handlerBtnModify(e);});

	}
	// 1. 콤보박스 설정하기
	public void ComboboxSetting() {
		ObservableList<String> listItem = FXCollections.observableArrayList("건의하기", "칭찬하기");
		comboBox.setItems(listItem);
	}
	// 2. 테이블뷰 컬럼 설정하기.
	public void TableViewSetting() {

		// 테이블 설정
		Tbldata = FXCollections.observableArrayList();
		// 편집 불가능하게 하기
		tableView.setDisable(false);
		// 2. 테이블 컬럼 설정하기.
		TableColumn colNo1 = new TableColumn("제목");
		colNo1.setMinWidth(250);
		colNo1.setStyle("-fx-alignment: CENTER;");
		colNo1.setCellValueFactory(new PropertyValueFactory("No1"));

		TableColumn colNo2 = new TableColumn("종류");
		colNo2.setMinWidth(250);
		colNo2.setStyle("-fx-alignment: CENTER;");
		colNo2.setCellValueFactory(new PropertyValueFactory("No2"));

		TableColumn colNo3 = new TableColumn("작성자");
		colNo3.setMinWidth(250);
		colNo3.setStyle("-fx-alignment: CENTER;");
		colNo3.setCellValueFactory(new PropertyValueFactory("No3"));
		// 테이블 컬럼 객체를 뷰에 리스트 추가 및 항목추가.
		tableView.setItems(Tbldata);
		tableView.getColumns().addAll(colNo1, colNo2, colNo3);
	}

	// 3. 작성한 내용을 테이블뷰에 저장한다.
	public void handlerBtnFinalAction(ActionEvent e) {
		// txtHuman comboBox txtTitle
		try {
			if (txtHuman.getText().equals("") || txtTitle.getText().equals("")) {
				System.out.println("등록 안돼");
				throw new Exception();
			} else {
				HospitalNo1 hsp = new HospitalNo1(txtTitle.getText(), comboBox.getSelectionModel().getSelectedItem(),
						txtHuman.getText());
				if(edit==true) {
					Tbldata.remove(selected);
					Tbldata.add(selected, hsp);
					edit=false;
				}else {
					Tbldata.add(hsp);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return ;
		}
		// 작성 완료시 공개여부를 잠군다.
//		rdoOpen.setDisable(true);
//		rdoOFF.setDisable(true);
		// 작성자 제목 비밀번호 콤보박스 작성된내용 초기화 시키는 함수 콜
		ResetContents();

	}

	// 4. 작성하기 버튼누르고 작성자 제목 비밀번호 콤보박스 내용 초기화 시키기.
	public void ResetContents() {
		txtArea.clear();
		txtHuman.clear();
		txtTitle.clear();
		txtPassword.clear();
		comboBox.getSelectionModel().clearSelection();
	}

	// 5. 테이블뷰 선택시 내용이 나오도록 한다.
	public void BtnTableViewPrint(MouseEvent e) {
		try {
			edit = true;
			selected = tableView.getSelectionModel().getFocusedIndex();
			 selected2 = tableView.getSelectionModel().getSelectedItems();
			txtTitle.setText( selected2.get(0).getNo1());
			comboBox.setValue( selected2.get(0).getNo2());
			txtHuman.setText( selected2.get(0).getNo3());
		} catch (Exception e2) {
			System.out.println("Null");
		}
		
	}

	// 6. 테이블뷰 내용을 삭제한다.
	public void handlerBtnDeleteAction(ActionEvent e) {
		Alert alert = new Alert(AlertType.WARNING);
		edit = false;
		Tbldata.remove(selected);
		txtHuman.setDisable(false);
		try {
			txtHuman.clear();
			comboBox.getSelectionModel().getSelectedItem();
			txtTitle.clear();
			alert.setTitle("알림");
			alert.setHeaderText("해당된 글을 선택하였습니다.");
			alert.setContentText("삭제완료");
			alert.showAndWait();

		} catch (Exception e3) {
			alert.setTitle("경고 창");
			alert.setHeaderText("오류가 발생하였습니다.");
			alert.setContentText("Error..");
			alert.showAndWait();
			e3.printStackTrace();
		}
	}

	// 7. 테이블뷰 내용을 수정한다.
	public void handlerBtnModify(ActionEvent e) {
			txtHuman.setDisable(true);
		try {
			Tbldata.remove(selected);
			Tbldata.add(selected , new HospitalNo1(txtHuman.getText(),comboBox.getSelectionModel().getSelectedItem(),txtTitle.getText()));
			txtHuman.setEditable(true);
			comboBox.getSelectionModel().clearSelection();
			txtTitle.setEditable(true);
		}catch(Exception e3) {
			e3.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수정 창");
			alert.setHeaderText("수정 실패");
			alert.setContentText("수정 하려는 목록을 정확하게 선택해주세요.");
		}
		
	}
	// 맨 처음에 테이블뷰에 값을 뿌린다.
	public void docTotalList() {
		ArrayList<noticeBoardVO> nblist = null;
		NoticeBoardDAO nbDAO = new NoticeBoardDAO();
		noticeBoardVO nbVO = null;
//		nblist = nbDAO.getDITotal();
		if (nblist == null) {
			PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 요망");
			return;
		}
		for (int i = 0; i < nblist.size(); i++) {
			nbVO = nblist.get(i);
//			Tbldata.add(nbVO);
		}
	}

}

