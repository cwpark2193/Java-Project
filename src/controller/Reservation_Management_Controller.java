package controller;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import model.ReservationVO;


public class Reservation_Management_Controller implements Initializable {
	@FXML DatePicker dpResDate;
	@FXML TableView<ReservationVO> tvResList;
	@FXML Button btnResRef;
	@FXML Button btnClear;
	@FXML Button btnPersonInfo;
	@FXML Button btnResClose;
	ObservableList<ReservationVO> resData;
	private int selectedDocIndex;
	public ObservableList<PatientVO> selectPatient;
	public ObservableList<ReservationVO> selectResvation;
	private File dirSave = new File("D:/images");
	private ObservableList<DoctorVO> selectDoctor;
	private String localUrl = ""; // �̹��� ���� ���
	private Image localImage;
	private File selectedFile = null;
	public DoctorVO doctorVO=null;
	public PatientVO patientVO=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ���̺� ����
		tvResSetin();
		// ��¥ ����
		dpResDate.setOnAction(e -> handlerDatePickerResDateAction(e));
		// ������ â �׼�
		btnPersonInfo.setOnAction(e -> handlerBtnPIAction(e));
		// ������� ��ư
		btnClear.setOnAction(e -> handlerBtnClearAction(e));
		// ���ΰ�ħ
		btnResRef.setOnAction(e -> handlerBtnResRefAction(e));
		// ���� ��ư �׼�
		btnResClose.setOnAction(e -> handlerBtnResCloseAction(e));
		// ���̺� �� Ŭ��
		tvResList.setOnMousePressed(e -> handlerResListTableViewPressedAction(e));
	}
	// ���ΰ�ħ
	public void handlerBtnResRefAction(ActionEvent e) {
		resTotalList();
	}
	// ���̺� ����
	public void tvResSetin() {
		// ���̺��� arraylist ����
		resData = FXCollections.observableArrayList();
		// tableview �� �������ϰ� ����
		tvResList.setEditable(false);
		
		TableColumn colResNum = new TableColumn("�����ȣ");
		colResNum.setPrefWidth(80);
		colResNum.setStyle("-fx-alignment: CENTER;");
		colResNum.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
		
		TableColumn colDocName = new TableColumn("�ǻ� �̸�");
		colDocName.setPrefWidth(90);
		colDocName.setStyle("-fx-alignment: CENTER;");
		colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
		
		TableColumn colDocPhone = new TableColumn("�ǻ� ����ó");
		colDocPhone.setPrefWidth(150);
		colDocPhone.setStyle("-fx-alignment: CENTER;");
		colDocPhone.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));
		
		
		TableColumn colPatName = new TableColumn("ȯ�� �̸�");
		colPatName.setPrefWidth(90);
		colPatName.setStyle("-fx-alignment: CENTER;");
		colPatName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
		
		TableColumn colPatPhone = new TableColumn("ȯ�� ����ó");
		colPatPhone.setPrefWidth(150);
		colPatPhone.setStyle("-fx-alignment: CENTER;");
		colPatPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));
		
		TableColumn colResDate = new TableColumn("���� ��¥");
		colResDate.setPrefWidth(120);
		colResDate.setStyle("-fx-alignment: CENTER;");
		colResDate.setCellValueFactory(new PropertyValueFactory<>("reservCareDate"));
		
		TableColumn colResTime = new TableColumn("���� �ð�");
		colResTime.setPrefWidth(90);
		colResTime.setStyle("-fx-alignment: CENTER;");
		colResTime.setCellValueFactory(new PropertyValueFactory<>("reservCaretime"));
		
		TableColumn colResReas = new TableColumn("���� ����");
		colResReas.setPrefWidth(220);
		colResReas.setStyle("-fx-alignment: CENTER;");
		colResReas.setCellValueFactory(new PropertyValueFactory<>("reservReaseon"));
		
		tvResList.setItems(resData);
		tvResList.getColumns().addAll(colResNum, colDocName, colDocPhone, colPatName, colPatPhone, colResDate, colResTime, colResReas);	
		resTotalList();
	}
	// �̹��� ���� ��ư
	public void handlerBtnMRCction(ActionEvent e) {
	
	}
	// ���̺� �� Ŭ��
	public void handlerResListTableViewPressedAction(MouseEvent e) {
		// �������� ��ġ�� �ش�� ��ü�� �����´�.
		selectedDocIndex = tvResList.getSelectionModel().getSelectedIndex();
		selectResvation = tvResList.getSelectionModel().getSelectedItems();
	}
	
	//��¥ �б�
	public void handlerDatePickerResDateAction(ActionEvent e) {
		resData.removeAll(resData);
		ArrayList<ReservationVO> list = null;
		ReservationDAO RDAO = new ReservationDAO();
		ReservationVO rvo = null;
	    String date=dpResDate.getValue().toString();
//	    System.out.println(dpResDate.getValue().toString()+"��");
	 
	    list = RDAO.getResDateCheck(date);
	
//	 System.out.println( list.get(0).getReservationNumber());
	 
		if (list == null) {
		   PMClass.alertDisplay(1, "���", "DB �������� ����", "���˿��");
		   return;
		
		} else {
		   for (int i = 0; i < list.size(); i++) {
		   	rvo = list.get(i);
		   	resData.add(rvo);
		   }
		}
	}
	// ������� ��ư
	public void handlerBtnClearAction(ActionEvent e) {
		try {
			selectResvation = tvResList.getSelectionModel().getSelectedItems();
			ReservationDAO RDAO = new ReservationDAO();
			RDAO.getResDelete(selectResvation.get(0).getReservationNumber());
			resData.removeAll(resData);
			resTotalList();
		} catch (Exception e1) {
//			PMClass.alertDisplay(1, "���� ����", "8��. ���� ����", e1.toString());
			e1.printStackTrace();
		}
	}
	//ȯ�� ���� â
	public void handlerBtnPIAction(ActionEvent e) {
		try {
			Parent PIView = FXMLLoader.load(getClass().getResource("/view/personidboard.fxml"));
			PMClass.callStage2(PIView, "���� ���� â");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//�ǻ� ���� â
	public void handlerBtnDIAction(ActionEvent e) {
		try {	
			Parent docInfoView=FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			PMClass.callStage2(docInfoView, "������â");
		}catch(Exception e1) {
			PMClass.alertDisplay(1,"â �ҷ����� ����","������â �ҷ����� ����",e1.toString()+e1.getMessage());
		}
	}
	//���� ��ư �׼�
	public void handlerBtnResCloseAction(ActionEvent e) {
		try {	
			Parent mainView=FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "������ ����â", btnResClose);
		}catch(Exception e1) {
			PMClass.alertDisplay(1,"���� �ҷ����� ����","����â �ҷ����� ����",e1.toString()+e1.getMessage());
		}
	}
	
	// ��ó���� �����̺�信 ����Ÿ���̽� ���� �о ���̺�信 �����´�.
	public void resTotalList() {
		resData.removeAll(resData);
		ArrayList<ReservationVO> reslist = null;
		ReservationDAO RDAO = new ReservationDAO();
		ReservationVO rvo = null;
		reslist = RDAO.getResTotal();
		if (reslist == null) {
			PMClass.alertDisplay(1, "���", "DB �������� ����", "���� ���");
			return;
		}
		for (int i = 0; i < reslist.size(); i++) {
			rvo = reslist.get(i);
			resData.add(rvo);
		}
	}
}
