package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Care_Management_MainVO;
import model.PatientVO;
import model.ReservationVO;

public class Care_Management_Main_Controller implements Initializable {
	@FXML DatePicker dpCaredate;
	@FXML ComboBox<String> cbCareTime;
	@FXML TableView<Care_Management_MainVO> tvCareList;
	@FXML Button btnCareRefresh;
	@FXML Button btnSave;
	@FXML Button btnClose;
	@FXML Button btnEdit;
	public int selectedCmmIndex;
	ObservableList<Care_Management_MainVO> careData;
	public ObservableList<Care_Management_MainVO> selectCMM;
	private boolean editDelete = false;
	private boolean editClose = false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// �޺��ڽ� ����
//		cbCareTime.setItems(FXCollections.observableArrayList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"));
		// ���̺� �� ����
		tvCareSetting();
		// ��� ��ư
		btnSave.setOnAction(e->handlerBtnSaveAction(e));
		// ���� ��ư
		btnEdit.setOnAction(e->handlerBtnEditAction(e));
		// ���� ��ư
		btnClose.setOnAction(e->handlerBtnCloseAction(e));
		// ���̺� Ŭ����
		tvCareList.setOnMousePressed(e->handleTvMousePressedAction(e));
		// ���̺� ���� Ŭ���� ���� �ҷ�����
		tvCareList.setOnMouseClicked(e->handlerCallCareInfoAction(e));
		// ��¥ �б�
		dpCaredate.setOnAction(e -> handlerDatePickerCareDateAction(e));
		// ���� ��ħ
		btnCareRefresh.setOnAction(e -> handlerBtnCareRefreshAction(e));
	}
	// ���� ��ħ
	public void handlerBtnCareRefreshAction(ActionEvent e) {
		careData.removeAll(careData);
		careTotalList();
		dpCaredate.getEditor().clear();
	}
	// ���̺� Ŭ����
	public void handleTvMousePressedAction(MouseEvent e) {
		selectedCmmIndex = tvCareList.getSelectionModel().getSelectedIndex();
		selectCMM = tvCareList.getSelectionModel().getSelectedItems();		
	}
	// ���̺� ���� Ŭ���� ���� �ҷ�����
	public void handlerCallCareInfoAction(MouseEvent e) {
		try {
			if (e.getClickCount() != 2) {
				return;
			}
			Parent careEdit = FXMLLoader.load(getClass().getResource("/view/care_management_content.fxml"));
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnEdit.getScene().getWindow());
			stageDialog.setTitle("������â");
			// =======================
			TextField editDocName = (TextField) careEdit.lookup("#tfDocName");
			TextField editDocPhone = (TextField) careEdit.lookup("#tfDocPhone");
			TextField editPatName = (TextField) careEdit.lookup("#tfPatName");
			TextField editPatPhone = (TextField) careEdit.lookup("#tfPatPhone");
			TextField editPay = (TextField) careEdit.lookup("#tfCarePay");
			DatePicker editCareDate = (DatePicker) careEdit.lookup("#dpCareDate");
			ComboBox<String> editTime = (ComboBox<String>) careEdit.lookup("#cbCareTime");
			TextArea editCareConts = (TextArea) careEdit.lookup("#taCareConts");
			TextArea editPresConts = (TextArea) careEdit.lookup("#taPresConts");
			Button editSave = (Button) careEdit.lookup("#btnSave");
			Button editClear = (Button) careEdit.lookup("#btnClear");
			Button editClose = (Button) careEdit.lookup("#btnCareClose");
			// =======================
			editDocName.setText(selectCMM.get(0).getDoctorName());
			editDocPhone.setText(selectCMM.get(0).getDoctorPhone());
			editPatName.setText(selectCMM.get(0).getPatientName());
			editPatPhone.setPromptText(selectCMM.get(0).getPatientPhone());
			editPay.setText(String.valueOf(selectCMM.get(0).getCarePay()));
			editCareDate.setPromptText(selectCMM.get(0).getReservCareDate());
			editTime.setPromptText(selectCMM.get(0).getReservCaretime());
			editCareConts.setText(selectCMM.get(0).getCareHistory());
			editPresConts.setText(selectCMM.get(0).getPrescription());
			// =======================
			editDocName.setDisable(true);
			editDocPhone.setDisable(true);
			editPatName.setDisable(true);
			editPatPhone.setDisable(true);
			editPay.setDisable(true);
			editCareDate.setDisable(true);
			editTime.setDisable(true);
			editCareConts.setDisable(true);
			editPresConts.setDisable(true);
			editSave.setDisable(true);
			editClear.setDisable(true);
			// =======================
			editClose.setOnAction(e3 -> {
				stageDialog.close();
			});
			Scene scene = new Scene(careEdit);
			stageDialog.setScene(scene);
			stageDialog.setResizable(false);
			stageDialog.show();
			// �����ư
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	// ���̺� �� ����
	public void tvCareSetting() {
	// ���̺��� arraylist ����
		careData = FXCollections.observableArrayList();
		// tableview �� �������ϰ� ����
		tvCareList.setEditable(false);
		
		TableColumn colCareNum = new TableColumn("�����ȣ");
		colCareNum.setPrefWidth(70);
		colCareNum.setStyle("-fx-alignment: CENTER;");
		colCareNum.setCellValueFactory(new PropertyValueFactory<>("careNumber"));
		
		TableColumn colDocName = new TableColumn("�ǻ� �̸�");
		colDocName.setPrefWidth(90);
		colDocName.setStyle("-fx-alignment: CENTER;");
		colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
				
		TableColumn colPatName = new TableColumn("ȯ�� �̸�");
		colPatName.setPrefWidth(90);
		colPatName.setStyle("-fx-alignment: CENTER;");
		colPatName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

		TableColumn colResDate = new TableColumn("���� ��¥");
		colResDate.setPrefWidth(130);
		colResDate.setStyle("-fx-alignment: CENTER;");
		colResDate.setCellValueFactory(new PropertyValueFactory<>("reservCareDate"));
		
		tvCareList.setItems(careData);
		tvCareList.getColumns().addAll(colCareNum, colDocName, colPatName, colResDate);	
		careTotalList();
	}
	// ��ó���� �����̺�信 ����Ÿ���̽� ���� �о ���̺�信 �����´�.
	public void careTotalList() {
		ArrayList<Care_Management_MainVO> carelist = null;
		Care_ManagementDAO RDAO = new Care_ManagementDAO();
		Care_Management_MainVO cmvo = null;
		carelist = RDAO.getCareTotal();
		if (carelist == null) {
			PMClass.alertDisplay(1, "���", "DB �������� ����", "���� ���");
			return;
		}
		for (int i = 0; i < carelist.size(); i++) {
			cmvo = carelist.get(i);
			careData.add(cmvo);
			if (editDelete == true) {
				carelist.remove(selectedCmmIndex);
				carelist.add(selectedCmmIndex,cmvo);
				editDelete = false;
			}
		}
	}
	// ��� ��ư
	public void handlerBtnSaveAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/care_management_content.fxml"));
			PMClass.callStage(mainView, "���� ��� ��â", btnClose);
		} catch (Exception e1) {
//			PMClass.alertDisplay(1, "���� �ҷ����� ����", "����â �ҷ����� ����", e1.toString() + e1.getMessage());
			e1.printStackTrace();
		}
	}
	// ���� ��ư
	public void handlerBtnEditAction(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/care_management_content.fxml"));
			Parent careEdit=loader.load();
			Care_Management_Content_Controller CmmCont=loader.getController();
			CmmCont.state=1;
			CmmCont.cmmVO = selectCMM.get(0);
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnEdit.getScene().getWindow());
			stageDialog.setTitle("������â");
			// =======================
			TextField editDocName = (TextField) careEdit.lookup("#tfDocName");
			TextField editDocPhone = (TextField) careEdit.lookup("#tfDocPhone");
			TextField editPatName = (TextField) careEdit.lookup("#tfPatName");
			TextField editPatPhone = (TextField) careEdit.lookup("#tfPatPhone");
			TextField editPay = (TextField) careEdit.lookup("#tfCarePay");
			DatePicker editCareDate = (DatePicker) careEdit.lookup("#dpCareDate");
			ComboBox<String> editTime = (ComboBox<String>) careEdit.lookup("#cbCareTime");
			TextArea editCareConts = (TextArea) careEdit.lookup("#taCareConts");
			TextArea editPresConts = (TextArea) careEdit.lookup("#taPresConts");
			Button editSave = (Button) careEdit.lookup("#btnSave");
			Button editClear = (Button) careEdit.lookup("#btnClear");
			Button editClose = (Button) careEdit.lookup("#btnCareClose");
			// =======================
			editDocName.setText(selectCMM.get(0).getDoctorName());
			editDocPhone.setText(selectCMM.get(0).getDoctorPhone());
			editPatName.setText(selectCMM.get(0).getPatientName());
			editPatPhone.setText(selectCMM.get(0).getPatientPhone());
			editPay.setText(String.valueOf(selectCMM.get(0).getCarePay()));
			editCareDate.setPromptText(selectCMM.get(0).getReservCareDate());
			editTime.setValue(selectCMM.get(0).getReservCaretime());
			editCareConts.setText(selectCMM.get(0).getCareHistory());
			editPresConts.setText(selectCMM.get(0).getPrescription());
			// =======================
			editClose.setOnAction(e3 -> {
				stageDialog.close();
			});
			Scene scene = new Scene(careEdit);
			stageDialog.setScene(scene);
			stageDialog.setResizable(false);
			stageDialog.show();
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "â �ҷ����� ����", "������â �ҷ����� ����", e1.toString() + e1.getMessage());
			e1.printStackTrace();
		}
	}
	// ���� ��ư
	public void handlerBtnCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "������ ����â", btnClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "���� �ҷ����� ����", "����â �ҷ����� ����", e1.toString() + e1.getMessage());
		}
	}
	//��¥ �б�
	public void handlerDatePickerCareDateAction(ActionEvent e) {
		careData.removeAll(careData);
		ArrayList<Care_Management_MainVO> list = null;
		Care_ManagementDAO CMDAO = new Care_ManagementDAO();
		Care_Management_MainVO cmvo = null;
	    String date=dpCaredate.getValue().toString();
//	    System.out.println(careData.getValue().toString()+"��");
	 
	    list = CMDAO.getCareDateCheck(date);
		
//		 System.out.println( list.get(0).getReservationNumber());
		 
			if (list == null) {
			   PMClass.alertDisplay(1, "���", "DB �������� ����", "���˿��");
			   return;
			
			} else {
			   for (int i = 0; i < list.size(); i++) {
				cmvo = list.get(i);
				careData.add(cmvo);
			   }
			}
		}
}
