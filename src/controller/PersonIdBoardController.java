package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DoctorVO;
import model.PatientVO;

public class PersonIdBoardController implements Initializable {
	@FXML
	private ImageView ivDoctor;
	@FXML
	private ImageView ivPatient;
	@FXML
	private ImageView ivDocRef;
	@FXML
	private ImageView ivPatRef;
	@FXML
	private TextField tfDocName;
	@FXML
	private TextField tfDocAge;
	@FXML
	private TextField tfDocPhone;
	@FXML
	private TextField tfDocAdd;
	@FXML
	private TextField tfPatAdd;
	@FXML
	private TextField tfPatPhone;
	@FXML
	private TextField tfPatAge;
	@FXML
	private TextField tfPatName;
	@FXML
	private Button btnDocEdit;
	@FXML
	private Button btnDocSet;
	@FXML
	private Button btnDocDel;
	@FXML
	private Button btnDocClose;
	@FXML
	private Button btnPatDel;
	@FXML
	private Button btnPatSet;
	@FXML
	private Button btnPatEdit;
	@FXML
	private Button btnPatClose;
	@FXML
	private Button btnDocRefresh;
	@FXML
	private Button btnPatRefresh;
	@FXML
	private TableView<DoctorVO> tvDocInfo;
	@FXML
	private TableView<PatientVO> tvPatInfo;
	private boolean editDelete = false;
	private File selectedFile = null;
	private File selectedFile2 = null;
	private int selectedDocIndex;
	private int selectedPatIndex;
	private File dirSave = new File("D:/images");
	private ObservableList<DoctorVO> selectDoctor;
	private ObservableList<PatientVO> selectPatient;
	private String localUrl = ""; // 이미지 파일 경로
	private Image localImage;
	private Image localImage2;
	ObservableList<DoctorVO> docData; // 의사테이블뷰에 보여주기위한 저장된 데이터
	ObservableList<PatientVO> patData; // 환자테이블뷰에 보여주기위한 저장된 데이터

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 1. 의사정보테이블설정기능
		tvDocInfoSeting();
		// 2. 환자정보테이블설정기능
		tvPatInfoSeting();
		// 3.의사정보창에서 수정 버튼 눌렀을 때
		btnDocEdit.setOnAction(e -> handlerBtnDocEditAction(e));
		// 4.의사정보창에서 등록 버튼 눌렀을 때
		btnDocSet.setOnAction(e -> handlerBtnDocSetAction(e));
		// 5.의사정보창에서 삭제 버튼 눌렀을 때
		btnDocDel.setOnAction(e -> handlerBtnDocDelAction(e));
		// 6.의사정보창에서 종료 버튼 눌렀을 때
		btnDocClose.setOnAction(e -> handlerBtnDocCloseAction(e));
		// 7.환자정보창에서 수정 버튼 눌렀을 때
		btnPatEdit.setOnAction(e -> handlerBtnPatEditAction(e));
		// 8.환자정보창에서 등록 버튼 눌렀을 때
		btnPatSet.setOnAction(e -> handlerBtnPatSetAction(e));
		// 9.환자정보창에서 삭제 버튼 눌렀을 때
		btnPatDel.setOnAction(e -> handlerBtnPatDelAction(e));
		// 10.환자정보창에서 종료 버튼 눌렀을 때
		btnPatClose.setOnAction(e -> handlerBtnPatCloseAction(e));
		// 11. 새로고침버튼 액션
		btnDocRefresh.setOnAction(e -> handlerBtnDocRefreshAction(e));
		btnPatRefresh.setOnAction(e -> handlerBtnPatRefreshAction(e));
		// 의사테이블 뷰 클릭
		tvDocInfo.setOnMousePressed(e -> handlerBtnDocTableViewPressedAction(e));
		// 환자테이블 뷰 클릭
		tvPatInfo.setOnMousePressed(e -> handlerBtnPatTableViewPressedAction(e));
		// 의사 테이블 뷰 더블 클릭
		tvDocInfo.setOnMouseClicked(e -> handlerCallDocInfoAction(e));
		// 환자 테이블 뷰 더블 클릭
		tvPatInfo.setOnMouseClicked(e -> handlerCallPatInfoAction(e));
	}

	// 환자 테이블 뷰 더블 클릭
	public void handlerCallPatInfoAction(MouseEvent e) {
		try {
			if (e.getClickCount() != 2) {
				return;
			}
			Parent patEdit = FXMLLoader.load(getClass().getResource("/view/patientinfo.fxml"));
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnPatEdit.getScene().getWindow());
			stageDialog.setTitle("상세정보창");
			// =======================
			TextField editId = (TextField) patEdit.lookup("#tfPatID");
			PasswordField editPass = (PasswordField) patEdit.lookup("#pfPatPass");
			TextField editName = (TextField) patEdit.lookup("#tfPatName");
			ComboBox<String> editGen = (ComboBox<String>) patEdit.lookup("#cbPatGen");
			TextField editAge = (TextField) patEdit.lookup("#tfPatAge");
			TextField editSsn = (TextField) patEdit.lookup("#tfPatSsn");
			TextField editPhone = (TextField) patEdit.lookup("#tfPatPhone");
			TextField editAdd = (TextField) patEdit.lookup("#tfPatAdd");
			ImageView editDP = (ImageView) patEdit.lookup("#ivPatImage");
			Button editIvBtn = (Button) patEdit.lookup("#btnImageSave");
			Button editSave = (Button) patEdit.lookup("#btnSave");
			Button editClear = (Button) patEdit.lookup("#btnClear");
			// ======================================
			String fileName = selectPatient.get(0).getPatientImage();
			selectedFile = new File("D:/images/" + fileName);
			localUrl = selectedFile.toURI().toURL().toString();
			localImage = new Image(localUrl, false);
			// =======================
			editId.setText(selectPatient.get(0).getPatientID());
			editPass.setText(selectPatient.get(0).getPatientPassword());
			editName.setText(selectPatient.get(0).getPatientName());
			editGen.setPromptText(selectPatient.get(0).getPatientGender());
			editAge.setText(String.valueOf(selectPatient.get(0).getPatientAge()));
			editSsn.setText(selectPatient.get(0).getPatientSSN());
			editPhone.setText(selectPatient.get(0).getPatientPhone());
			editAdd.setText(selectPatient.get(0).getPatientAddress());
			editDP.setImage(localImage);
			// =======================
			editId.setDisable(true);
			editPass.setDisable(true);
			editName.setDisable(true);
			editGen.setDisable(true);
			editAge.setDisable(true);
			editSsn.setDisable(true);
			editPhone.setDisable(true);
			editAdd.setDisable(true);
			editIvBtn.setDisable(true);
			editSave.setDisable(true);
			editClear.setDisable(true);
			// =======================
			Scene scene = new Scene(patEdit);
			stageDialog.setScene(scene);
			stageDialog.setResizable(false);
			stageDialog.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// 의사 테이블 뷰 더블 클릭
	public void handlerCallDocInfoAction(MouseEvent e) {
		try {
			if (e.getClickCount() != 2) {
				return;
			}
			Parent docEdit = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnDocEdit.getScene().getWindow());
			stageDialog.setTitle("상세정보창");
			// =======================
			TextField editId = (TextField) docEdit.lookup("#tfDocID");
			PasswordField editPass = (PasswordField) docEdit.lookup("#pfDocPass");
			TextField editName = (TextField) docEdit.lookup("#tfDocName");
			ComboBox<String> editGen = (ComboBox<String>) docEdit.lookup("#cbDocGen");
			TextField editAge = (TextField) docEdit.lookup("#tfDocAge");
			TextField editSsn = (TextField) docEdit.lookup("#tfDocSsn");
			TextField editPhone = (TextField) docEdit.lookup("#tfDocPhone");
			TextField editAdd = (TextField) docEdit.lookup("#tfDocAdd");
			ComboBox<String> editTime = (ComboBox<String>) docEdit.lookup("#cbDocTime");
			TextField editLn = (TextField) docEdit.lookup("#tfDocLN");
			ImageView editDP = (ImageView) docEdit.lookup("#ivDocPhoto");
			ImageView editDLP = (ImageView) docEdit.lookup("#ivDocLP");
			Button editIvBtn = (Button) docEdit.lookup("#btnDocIvSet");
			Button editSave = (Button) docEdit.lookup("#btnSave");
			Button editClear = (Button) docEdit.lookup("#btnClear");
			// ======================================
			String fileName = selectDoctor.get(0).getDoctorImage();
			selectedFile = new File("D:/images/" + fileName);
			localUrl = selectedFile.toURI().toURL().toString();
			localImage = new Image(localUrl, false);
			// =======================
			editId.setText(selectDoctor.get(0).getDoctorID());
			editPass.setText(selectDoctor.get(0).getDoctorPassword());
			editName.setText(selectDoctor.get(0).getDoctorName());
			editGen.setPromptText(selectDoctor.get(0).getDoctorGender());
			editAge.setText(String.valueOf(selectDoctor.get(0).getDoctorAge()));
			editSsn.setText(selectDoctor.get(0).getDoctorSSN());
			editPhone.setText(selectDoctor.get(0).getDoctorPhone());
			editAdd.setText(selectDoctor.get(0).getDoctorAddress());
			editTime.setPromptText(selectDoctor.get(0).getDoctorCareTime());
			editLn.setText(selectDoctor.get(0).getDoctorLicense());
			editDP.setImage(localImage);
			// =======================
			editId.setDisable(true);
			editPass.setDisable(true);
			editName.setDisable(true);
			editGen.setDisable(true);
			editAge.setDisable(true);
			editSsn.setDisable(true);
			editPhone.setDisable(true);
			editAdd.setDisable(true);
			editTime.setDisable(true);
			editLn.setDisable(true);
			editIvBtn.setDisable(true);
			editSave.setDisable(true);
			editClear.setDisable(true);
			// =======================
			Scene scene = new Scene(docEdit);
			stageDialog.setScene(scene);
			stageDialog.setResizable(false);
			stageDialog.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// 환자테이블 뷰 클릭
	public void handlerBtnPatTableViewPressedAction(MouseEvent e) {
		try {
			// 눌렀을떄 위치와 해당된 객체를 가져온다.
			selectedPatIndex = tvPatInfo.getSelectionModel().getSelectedIndex();
			selectPatient = tvPatInfo.getSelectionModel().getSelectedItems();
			//

			tfPatName.setText(selectPatient.get(0).getPatientName());
			tfPatAge.setText(String.valueOf(selectPatient.get(0).getPatientAge()));
			tfPatPhone.setText(selectPatient.get(0).getPatientPhone());
			tfPatAdd.setText(selectPatient.get(0).getPatientAddress());

			String fileName = selectPatient.get(0).getPatientImage();
			selectedFile = new File("D:/images/" + fileName);
			if (selectedFile != null) {
				ivInit(ivPatient);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	// 테이블에서 새로고침 버튼 눌렀을 때-환자
	public void handlerBtnPatRefreshAction(ActionEvent e) {
		patData.removeAll(patData);
		patTotalList();
		tfPatName.clear();
		tfPatAge.clear();
		tfPatPhone.clear();
		tfPatAdd.clear();
		localUrl = "/images/profilStandard.png";
		localImage = new Image(localUrl, false);
		ivPatient.setImage(localImage);
	}

	// 테이블 새로고침 버튼-의사
	public void handlerBtnDocRefreshAction(ActionEvent e) {
		docData.removeAll(docData);
		docTotalList();
		tfDocName.clear();
		tfDocAge.clear();
		tfDocPhone.clear();
		tfDocAdd.clear();
		localUrl = "/images/profilStandard.png";
		localImage = new Image(localUrl, false);
		ivDoctor.setImage(localImage);
	}

	// 테이블 뷰 클릭
	public void handlerBtnDocTableViewPressedAction(MouseEvent e) {
		try {
			// 눌렀을떄 위치와 해당된 객체를 가져온다.
			editDelete = true;
			selectedDocIndex = tvDocInfo.getSelectionModel().getSelectedIndex();
			selectDoctor = tvDocInfo.getSelectionModel().getSelectedItems();
			// 눌렀을 때 왼쪽 필드에 값 삽입
			tfDocName.setText(selectDoctor.get(0).getDoctorName());
			tfDocAge.setText(String.valueOf(selectDoctor.get(0).getDoctorAge()));
			tfDocPhone.setText(selectDoctor.get(0).getDoctorPhone());
			tfDocAdd.setText(selectDoctor.get(0).getDoctorAddress());

			String fileName = selectDoctor.get(0).getDoctorImage();
			selectedFile = new File("D:/images/" + fileName);
			if (selectedFile != null) {
				ivInit(ivDoctor);
			}
		} catch (Exception e2) {
			editDelete = true;
			e2.printStackTrace();
		}
	}

	// 환자정보테이블설정기능
	public void tvPatInfoSeting() {
		patData = FXCollections.observableArrayList();
		// tableview 를 편집못하게 설정
		tvPatInfo.setEditable(false);

		TableColumn colPatName = new TableColumn("이름");
		colPatName.setPrefWidth(90);
		colPatName.setStyle("-fx-alignment: CENTER");
		colPatName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

		TableColumn colPatGen = new TableColumn("성별");
		colPatGen.setPrefWidth(40);
		colPatGen.setStyle("-fx-alignment: CENTER");
		colPatGen.setCellValueFactory(new PropertyValueFactory<>("patientGender"));

		TableColumn colPatAge = new TableColumn("나이");
		colPatAge.setPrefWidth(50);
		colPatAge.setStyle("-fx-alignment: CENTER");
		colPatAge.setCellValueFactory(new PropertyValueFactory<>("patientAge"));

		TableColumn colPatPhone = new TableColumn("연락처");
		colPatPhone.setPrefWidth(140);
		colPatPhone.setStyle("-fx-alignment: CENTER");
		colPatPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));

		TableColumn colPatAdd = new TableColumn("주소");
		colPatAdd.setPrefWidth(150);
		colPatAdd.setStyle("-fx-alignment: CENTER");
		colPatAdd.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));

		tvPatInfo.setItems(patData);
		tvPatInfo.getColumns().addAll(colPatName, colPatGen, colPatAge, colPatPhone, colPatAdd);
		patTotalList();
	}

	// 의사정보테이블설정기능
	public void tvDocInfoSeting() {
		// 테이블설정 arraylist 설정
		docData = FXCollections.observableArrayList();
		// tableview 를 편집못하게 설정
		tvDocInfo.setEditable(false);

		TableColumn colDocName = new TableColumn("이름");
		colDocName.setPrefWidth(90);
		colDocName.setStyle("-fx-alignment: CENTER;");
		colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

		TableColumn colDocGen = new TableColumn("성별");
		colDocGen.setPrefWidth(40);
		colDocGen.setStyle("-fx-alignment: CENTER;");
		colDocGen.setCellValueFactory(new PropertyValueFactory<>("doctorGender"));

		TableColumn colDocAge = new TableColumn("나이");
		colDocAge.setPrefWidth(50);
		colDocAge.setStyle("-fx-alignment: CENTER;");
		colDocAge.setCellValueFactory(new PropertyValueFactory<>("doctorAge"));

		TableColumn colDocPhone = new TableColumn("연락처");
		colDocPhone.setPrefWidth(140);
		colDocPhone.setStyle("-fx-alignment: CENTER;");
		colDocPhone.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));

		TableColumn colDocAdd = new TableColumn("주소");
		colDocAdd.setPrefWidth(150);
		colDocAdd.setStyle("-fx-alignment: CENTER;");
		colDocAdd.setCellValueFactory(new PropertyValueFactory<>("doctorAddress"));

		tvDocInfo.setItems(docData);
		tvDocInfo.getColumns().addAll(colDocName, colDocGen, colDocAge, colDocPhone, colDocAdd);
		docTotalList();
	}

	// 1.의사정보창에서 수정 버튼 눌렀을 때
	public void handlerBtnDocEditAction(ActionEvent e) {
		try {
			// 창 불러오기
//			Parent docEdit = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctorinfo.fxml"));
			Parent docEdit = loader.load();
			DoctorInfoController docInfoCont = loader.getController();
			docInfoCont.state = 1;
			docInfoCont.doctorVO = selectDoctor.get(0);
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnDocEdit.getScene().getWindow());
			stageDialog.setTitle("상세정보창");
			// =======================
			TextField editId = (TextField) docEdit.lookup("#tfDocID");
			PasswordField editPass = (PasswordField) docEdit.lookup("#pfDocPass");
			TextField editName = (TextField) docEdit.lookup("#tfDocName");
			ComboBox<String> editGen = (ComboBox<String>) docEdit.lookup("#cbDocGen");
			TextField editAge = (TextField) docEdit.lookup("#tfDocAge");
			TextField editSsn = (TextField) docEdit.lookup("#tfDocSsn");
			TextField editPhone = (TextField) docEdit.lookup("#tfDocPhone");
			TextField editAdd = (TextField) docEdit.lookup("#tfDocAdd");
			ComboBox<String> editTime = (ComboBox<String>) docEdit.lookup("#cbDocTime");
			TextField editLn = (TextField) docEdit.lookup("#tfDocLN");
			ImageView editDP = (ImageView) docEdit.lookup("#ivDocPhoto");
			Button editIvBtn = (Button) docEdit.lookup("#btnDocIvSet");
			Button editSave = (Button) docEdit.lookup("#btnSave");
			Button editClear = (Button) docEdit.lookup("#btnClear");

			String fileName = selectDoctor.get(0).getDoctorImage();
			selectedFile = new File("D:/images/" + fileName);
			localUrl = selectedFile.toURI().toURL().toString();
			localImage = new Image(localUrl, false);
			// =======================
			editId.setText(selectDoctor.get(0).getDoctorID());
			editPass.setText(selectDoctor.get(0).getDoctorPassword());
			editName.setText(selectDoctor.get(0).getDoctorName());
			editGen.setPromptText(selectDoctor.get(0).getDoctorGender());
			editAge.setText(String.valueOf(selectDoctor.get(0).getDoctorAge()));
			editSsn.setText(selectDoctor.get(0).getDoctorSSN());
			editPhone.setText(selectDoctor.get(0).getDoctorPhone());
			editAdd.setText(selectDoctor.get(0).getDoctorAddress());
			editTime.setPromptText(selectDoctor.get(0).getDoctorCareTime());
			editLn.setText(selectDoctor.get(0).getDoctorLicense());
			editDP.setImage(localImage);
			// =======================
			Scene scene = new Scene(docEdit);
			stageDialog.setScene(scene);
			stageDialog.setResizable(false);
			stageDialog.show();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "창 불러오기 실패", "상세정보창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}

	// 2.의사정보창에서 등록 버튼 눌렀을 때
	public void handlerBtnDocSetAction(ActionEvent e) {
		try {
			Parent docInfoView = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			PMClass.callStage2(docInfoView, "상세정보창");
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "창 불러오기 실패", "상세정보창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}

	// 3.의사정보창에서 삭제 버튼 눌렀을 때
	public void handlerBtnDocDelAction(ActionEvent e) {
		try {
			DoctorInfoDAO DIDAO = new DoctorInfoDAO();
			DIDAO.getDIDelete(selectDoctor.get(0).getDoctorNumber());
			docData.removeAll(docData);
			docTotalList();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "삭제 오류", "8번. 삭제 실패", e1.toString());
		}
	}

	// 4.의사정보창에서 종료 버튼 눌렀을 때
	public void handlerBtnDocCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "관리자 메인창", btnDocClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}

	// 5.환자정보창에서 수정 버튼 눌렀을 때
	public void handlerBtnPatEditAction(ActionEvent e) {
		try {
			// 창 불러오기
//			Parent docEdit = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patientinfo.fxml"));
			Parent patEdit = loader.load();
			PatientInfoController patInfoCont = loader.getController();
			patInfoCont.state = 1;
			patInfoCont.patientVO = selectPatient.get(0);
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnDocEdit.getScene().getWindow());
			stageDialog.setTitle("상세정보창");
			// =======================
			TextField editId = (TextField) patEdit.lookup("#tfPatID");
			PasswordField editPass = (PasswordField) patEdit.lookup("#pfPatPass");
			TextField editName = (TextField) patEdit.lookup("#tfPatName");
			ComboBox<String> editGen = (ComboBox<String>) patEdit.lookup("#cbPatGen");
			TextField editAge = (TextField) patEdit.lookup("#tfPatAge");
			TextField editSsn = (TextField) patEdit.lookup("#tfPatSsn");
			TextField editPhone = (TextField) patEdit.lookup("#tfPatPhone");
			TextField editAdd = (TextField) patEdit.lookup("#tfPatAdd");
			ImageView editDP = (ImageView) patEdit.lookup("#ivPatImage");
			Button editIvBtn = (Button) patEdit.lookup("#btnImageSave");
			Button editSave = (Button) patEdit.lookup("#btnSave");
			Button editClear = (Button) patEdit.lookup("#btnClear");

			String fileName = selectPatient.get(0).getPatientImage();
			selectedFile = new File("D:/images/" + fileName);
			localUrl = selectedFile.toURI().toURL().toString();
			localImage = new Image(localUrl, false);
			// =======================
			editId.setText(selectPatient.get(0).getPatientID());
			editPass.setText(selectPatient.get(0).getPatientPassword());
			editName.setText(selectPatient.get(0).getPatientName());
			editGen.setPromptText(selectPatient.get(0).getPatientGender());
			editAge.setText(String.valueOf(selectPatient.get(0).getPatientAge()));
			editSsn.setText(selectPatient.get(0).getPatientSSN());
			editPhone.setText(selectPatient.get(0).getPatientPhone());
			editAdd.setText(selectPatient.get(0).getPatientAddress());
			editDP.setImage(localImage);
			// =======================
			Scene scene = new Scene(patEdit);
			stageDialog.setScene(scene);
			stageDialog.setResizable(false);
			stageDialog.show();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "창 불러오기 실패", "상세정보창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}

	// 6.환자정보창에서 등록 버튼 눌렀을 때
	public void handlerBtnPatSetAction(ActionEvent e) {
		try {
			Parent patInfoView = FXMLLoader.load(getClass().getResource("/view/patientinfo.fxml"));
			PMClass.callStage2(patInfoView, "상세정보창");
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "창 불러오기 실패", "상세정보창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}

	// 7.환자정보창에서 삭제 버튼 눌렀을 때
	public void handlerBtnPatDelAction(ActionEvent e) {
		try {
			PatientDAO pdao = new PatientDAO();
			pdao.getPIDelete(selectPatient.get(0).getPatientNumber());
			patData.removeAll(patData);
			patTotalList();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "삭제 오류", "8번. 삭제 실패", e1.toString());
		}
	}

	// 8.환자정보창에서 종료 버튼 눌렀을 때
	public void handlerBtnPatCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "관리자 메인창", btnPatClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}

	// 맨처음에 의테이블뷰에 데이타베이스 값을 읽어서 테이블뷰에 가져온다.
	public void docTotalList() {
		ArrayList<DoctorVO> dvlist = null;
		DoctorInfoDAO DIDAO = new DoctorInfoDAO();
		DoctorVO doctorVO = null;
		dvlist = DIDAO.getDITotal();
		if (dvlist == null) {
			PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 요망");
			return;
		}
		for (int i = 0; i < dvlist.size(); i++) {
			doctorVO = dvlist.get(i);
			docData.add(doctorVO);
		}
	}

	// 맨처음에 의테이블뷰에 데이타베이스 값을 읽어서 테이블뷰에 가져온다.
	public void patTotalList() {
		ArrayList<PatientVO> pvlist = null;
		PatientDAO PIDAO = new PatientDAO();
		PatientVO pvo = null;
		pvlist = PIDAO.getPITotal();
		if (pvlist == null) {
			PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 요망");
			return;
		}
		for (int i = 0; i < pvlist.size(); i++) {
			pvo = pvlist.get(i);
			patData.add(pvo);
		}
	}

	// 사진 초기화
	public void ivInit(ImageView iv) {
		try {
			localUrl = selectedFile.toURI().toURL().toString();
			localImage = new Image(localUrl, false);
			iv.setImage(localImage);
			iv.setFitHeight(100);
			iv.setFitWidth(100);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	// 이미지 저장
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// 이미지 파일명 생성
			fileName = "doctorInfo" + System.currentTimeMillis() + "_" + file.getName();
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));

			// 선택한 이미지 파일 InputStream의 마지막에 이르렀을 경우는 -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return fileName;
	}
}
