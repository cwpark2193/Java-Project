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
	private String localUrl = ""; // �̹��� ���� ���
	private Image localImage;
	private Image localImage2;
	ObservableList<DoctorVO> docData; // �ǻ����̺�信 �����ֱ����� ����� ������
	ObservableList<PatientVO> patData; // ȯ�����̺�信 �����ֱ����� ����� ������

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 1. �ǻ��������̺������
		tvDocInfoSeting();
		// 2. ȯ���������̺������
		tvPatInfoSeting();
		// 3.�ǻ�����â���� ���� ��ư ������ ��
		btnDocEdit.setOnAction(e -> handlerBtnDocEditAction(e));
		// 4.�ǻ�����â���� ��� ��ư ������ ��
		btnDocSet.setOnAction(e -> handlerBtnDocSetAction(e));
		// 5.�ǻ�����â���� ���� ��ư ������ ��
		btnDocDel.setOnAction(e -> handlerBtnDocDelAction(e));
		// 6.�ǻ�����â���� ���� ��ư ������ ��
		btnDocClose.setOnAction(e -> handlerBtnDocCloseAction(e));
		// 7.ȯ������â���� ���� ��ư ������ ��
		btnPatEdit.setOnAction(e -> handlerBtnPatEditAction(e));
		// 8.ȯ������â���� ��� ��ư ������ ��
		btnPatSet.setOnAction(e -> handlerBtnPatSetAction(e));
		// 9.ȯ������â���� ���� ��ư ������ ��
		btnPatDel.setOnAction(e -> handlerBtnPatDelAction(e));
		// 10.ȯ������â���� ���� ��ư ������ ��
		btnPatClose.setOnAction(e -> handlerBtnPatCloseAction(e));
		// 11. ���ΰ�ħ��ư �׼�
		btnDocRefresh.setOnAction(e -> handlerBtnDocRefreshAction(e));
		btnPatRefresh.setOnAction(e -> handlerBtnPatRefreshAction(e));
		// �ǻ����̺� �� Ŭ��
		tvDocInfo.setOnMousePressed(e -> handlerBtnDocTableViewPressedAction(e));
		// ȯ�����̺� �� Ŭ��
		tvPatInfo.setOnMousePressed(e -> handlerBtnPatTableViewPressedAction(e));
		// �ǻ� ���̺� �� ���� Ŭ��
		tvDocInfo.setOnMouseClicked(e -> handlerCallDocInfoAction(e));
		// ȯ�� ���̺� �� ���� Ŭ��
		tvPatInfo.setOnMouseClicked(e -> handlerCallPatInfoAction(e));
	}

	// ȯ�� ���̺� �� ���� Ŭ��
	public void handlerCallPatInfoAction(MouseEvent e) {
		try {
			if (e.getClickCount() != 2) {
				return;
			}
			Parent patEdit = FXMLLoader.load(getClass().getResource("/view/patientinfo.fxml"));
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnPatEdit.getScene().getWindow());
			stageDialog.setTitle("������â");
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

	// �ǻ� ���̺� �� ���� Ŭ��
	public void handlerCallDocInfoAction(MouseEvent e) {
		try {
			if (e.getClickCount() != 2) {
				return;
			}
			Parent docEdit = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnDocEdit.getScene().getWindow());
			stageDialog.setTitle("������â");
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

	// ȯ�����̺� �� Ŭ��
	public void handlerBtnPatTableViewPressedAction(MouseEvent e) {
		try {
			// �������� ��ġ�� �ش�� ��ü�� �����´�.
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

	// ���̺��� ���ΰ�ħ ��ư ������ ��-ȯ��
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

	// ���̺� ���ΰ�ħ ��ư-�ǻ�
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

	// ���̺� �� Ŭ��
	public void handlerBtnDocTableViewPressedAction(MouseEvent e) {
		try {
			// �������� ��ġ�� �ش�� ��ü�� �����´�.
			editDelete = true;
			selectedDocIndex = tvDocInfo.getSelectionModel().getSelectedIndex();
			selectDoctor = tvDocInfo.getSelectionModel().getSelectedItems();
			// ������ �� ���� �ʵ忡 �� ����
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

	// ȯ���������̺������
	public void tvPatInfoSeting() {
		patData = FXCollections.observableArrayList();
		// tableview �� �������ϰ� ����
		tvPatInfo.setEditable(false);

		TableColumn colPatName = new TableColumn("�̸�");
		colPatName.setPrefWidth(90);
		colPatName.setStyle("-fx-alignment: CENTER");
		colPatName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

		TableColumn colPatGen = new TableColumn("����");
		colPatGen.setPrefWidth(40);
		colPatGen.setStyle("-fx-alignment: CENTER");
		colPatGen.setCellValueFactory(new PropertyValueFactory<>("patientGender"));

		TableColumn colPatAge = new TableColumn("����");
		colPatAge.setPrefWidth(50);
		colPatAge.setStyle("-fx-alignment: CENTER");
		colPatAge.setCellValueFactory(new PropertyValueFactory<>("patientAge"));

		TableColumn colPatPhone = new TableColumn("����ó");
		colPatPhone.setPrefWidth(140);
		colPatPhone.setStyle("-fx-alignment: CENTER");
		colPatPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));

		TableColumn colPatAdd = new TableColumn("�ּ�");
		colPatAdd.setPrefWidth(150);
		colPatAdd.setStyle("-fx-alignment: CENTER");
		colPatAdd.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));

		tvPatInfo.setItems(patData);
		tvPatInfo.getColumns().addAll(colPatName, colPatGen, colPatAge, colPatPhone, colPatAdd);
		patTotalList();
	}

	// �ǻ��������̺������
	public void tvDocInfoSeting() {
		// ���̺��� arraylist ����
		docData = FXCollections.observableArrayList();
		// tableview �� �������ϰ� ����
		tvDocInfo.setEditable(false);

		TableColumn colDocName = new TableColumn("�̸�");
		colDocName.setPrefWidth(90);
		colDocName.setStyle("-fx-alignment: CENTER;");
		colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

		TableColumn colDocGen = new TableColumn("����");
		colDocGen.setPrefWidth(40);
		colDocGen.setStyle("-fx-alignment: CENTER;");
		colDocGen.setCellValueFactory(new PropertyValueFactory<>("doctorGender"));

		TableColumn colDocAge = new TableColumn("����");
		colDocAge.setPrefWidth(50);
		colDocAge.setStyle("-fx-alignment: CENTER;");
		colDocAge.setCellValueFactory(new PropertyValueFactory<>("doctorAge"));

		TableColumn colDocPhone = new TableColumn("����ó");
		colDocPhone.setPrefWidth(140);
		colDocPhone.setStyle("-fx-alignment: CENTER;");
		colDocPhone.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));

		TableColumn colDocAdd = new TableColumn("�ּ�");
		colDocAdd.setPrefWidth(150);
		colDocAdd.setStyle("-fx-alignment: CENTER;");
		colDocAdd.setCellValueFactory(new PropertyValueFactory<>("doctorAddress"));

		tvDocInfo.setItems(docData);
		tvDocInfo.getColumns().addAll(colDocName, colDocGen, colDocAge, colDocPhone, colDocAdd);
		docTotalList();
	}

	// 1.�ǻ�����â���� ���� ��ư ������ ��
	public void handlerBtnDocEditAction(ActionEvent e) {
		try {
			// â �ҷ�����
//			Parent docEdit = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctorinfo.fxml"));
			Parent docEdit = loader.load();
			DoctorInfoController docInfoCont = loader.getController();
			docInfoCont.state = 1;
			docInfoCont.doctorVO = selectDoctor.get(0);
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnDocEdit.getScene().getWindow());
			stageDialog.setTitle("������â");
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
			PMClass.alertDisplay(1, "â �ҷ����� ����", "������â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}

	// 2.�ǻ�����â���� ��� ��ư ������ ��
	public void handlerBtnDocSetAction(ActionEvent e) {
		try {
			Parent docInfoView = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			PMClass.callStage2(docInfoView, "������â");
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "â �ҷ����� ����", "������â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}

	// 3.�ǻ�����â���� ���� ��ư ������ ��
	public void handlerBtnDocDelAction(ActionEvent e) {
		try {
			DoctorInfoDAO DIDAO = new DoctorInfoDAO();
			DIDAO.getDIDelete(selectDoctor.get(0).getDoctorNumber());
			docData.removeAll(docData);
			docTotalList();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� ����", "8��. ���� ����", e1.toString());
		}
	}

	// 4.�ǻ�����â���� ���� ��ư ������ ��
	public void handlerBtnDocCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "������ ����â", btnDocClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� �ҷ����� ����", "����â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}

	// 5.ȯ������â���� ���� ��ư ������ ��
	public void handlerBtnPatEditAction(ActionEvent e) {
		try {
			// â �ҷ�����
//			Parent docEdit = FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patientinfo.fxml"));
			Parent patEdit = loader.load();
			PatientInfoController patInfoCont = loader.getController();
			patInfoCont.state = 1;
			patInfoCont.patientVO = selectPatient.get(0);
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnDocEdit.getScene().getWindow());
			stageDialog.setTitle("������â");
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
			PMClass.alertDisplay(1, "â �ҷ����� ����", "������â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}

	// 6.ȯ������â���� ��� ��ư ������ ��
	public void handlerBtnPatSetAction(ActionEvent e) {
		try {
			Parent patInfoView = FXMLLoader.load(getClass().getResource("/view/patientinfo.fxml"));
			PMClass.callStage2(patInfoView, "������â");
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "â �ҷ����� ����", "������â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}

	// 7.ȯ������â���� ���� ��ư ������ ��
	public void handlerBtnPatDelAction(ActionEvent e) {
		try {
			PatientDAO pdao = new PatientDAO();
			pdao.getPIDelete(selectPatient.get(0).getPatientNumber());
			patData.removeAll(patData);
			patTotalList();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� ����", "8��. ���� ����", e1.toString());
		}
	}

	// 8.ȯ������â���� ���� ��ư ������ ��
	public void handlerBtnPatCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "������ ����â", btnPatClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� �ҷ����� ����", "����â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}

	// ��ó���� �����̺�信 ����Ÿ���̽� ���� �о ���̺�信 �����´�.
	public void docTotalList() {
		ArrayList<DoctorVO> dvlist = null;
		DoctorInfoDAO DIDAO = new DoctorInfoDAO();
		DoctorVO doctorVO = null;
		dvlist = DIDAO.getDITotal();
		if (dvlist == null) {
			PMClass.alertDisplay(1, "���", "DB �������� ����", "���� ���");
			return;
		}
		for (int i = 0; i < dvlist.size(); i++) {
			doctorVO = dvlist.get(i);
			docData.add(doctorVO);
		}
	}

	// ��ó���� �����̺�信 ����Ÿ���̽� ���� �о ���̺�信 �����´�.
	public void patTotalList() {
		ArrayList<PatientVO> pvlist = null;
		PatientDAO PIDAO = new PatientDAO();
		PatientVO pvo = null;
		pvlist = PIDAO.getPITotal();
		if (pvlist == null) {
			PMClass.alertDisplay(1, "���", "DB �������� ����", "���� ���");
			return;
		}
		for (int i = 0; i < pvlist.size(); i++) {
			pvo = pvlist.get(i);
			patData.add(pvo);
		}
	}

	// ���� �ʱ�ȭ
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

	// �̹��� ����
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// �̹��� ���ϸ� ����
			fileName = "doctorInfo" + System.currentTimeMillis() + "_" + file.getName();
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));

			// ������ �̹��� ���� InputStream�� �������� �̸����� ���� -1
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
