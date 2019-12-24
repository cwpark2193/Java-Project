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
		// 콤보박스 세팅
//		cbCareTime.setItems(FXCollections.observableArrayList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"));
		// 테이블 뷰 세팅
		tvCareSetting();
		// 등록 버튼
		btnSave.setOnAction(e->handlerBtnSaveAction(e));
		// 수정 버튼
		btnEdit.setOnAction(e->handlerBtnEditAction(e));
		// 종료 버튼
		btnClose.setOnAction(e->handlerBtnCloseAction(e));
		// 테이블 클릭시
		tvCareList.setOnMousePressed(e->handleTvMousePressedAction(e));
		// 테이블 더블 클릭시 내용 불러오기
		tvCareList.setOnMouseClicked(e->handlerCallCareInfoAction(e));
		// 날짜 읽기
		dpCaredate.setOnAction(e -> handlerDatePickerCareDateAction(e));
		// 새로 고침
		btnCareRefresh.setOnAction(e -> handlerBtnCareRefreshAction(e));
	}
	// 새로 고침
	public void handlerBtnCareRefreshAction(ActionEvent e) {
		careData.removeAll(careData);
		careTotalList();
		dpCaredate.getEditor().clear();
	}
	// 테이블 클릭시
	public void handleTvMousePressedAction(MouseEvent e) {
		selectedCmmIndex = tvCareList.getSelectionModel().getSelectedIndex();
		selectCMM = tvCareList.getSelectionModel().getSelectedItems();		
	}
	// 테이블 더블 클릭시 내용 불러오기
	public void handlerCallCareInfoAction(MouseEvent e) {
		try {
			if (e.getClickCount() != 2) {
				return;
			}
			Parent careEdit = FXMLLoader.load(getClass().getResource("/view/care_management_content.fxml"));
			Stage stageDialog = new Stage(StageStyle.UTILITY);
			stageDialog.initModality(Modality.WINDOW_MODAL);
			stageDialog.initOwner(btnEdit.getScene().getWindow());
			stageDialog.setTitle("상세정보창");
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
			// 종료버튼
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	// 테이블 뷰 세팅
	public void tvCareSetting() {
	// 테이블설정 arraylist 설정
		careData = FXCollections.observableArrayList();
		// tableview 를 편집못하게 설정
		tvCareList.setEditable(false);
		
		TableColumn colCareNum = new TableColumn("진료번호");
		colCareNum.setPrefWidth(70);
		colCareNum.setStyle("-fx-alignment: CENTER;");
		colCareNum.setCellValueFactory(new PropertyValueFactory<>("careNumber"));
		
		TableColumn colDocName = new TableColumn("의사 이름");
		colDocName.setPrefWidth(90);
		colDocName.setStyle("-fx-alignment: CENTER;");
		colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
				
		TableColumn colPatName = new TableColumn("환자 이름");
		colPatName.setPrefWidth(90);
		colPatName.setStyle("-fx-alignment: CENTER;");
		colPatName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

		TableColumn colResDate = new TableColumn("예약 날짜");
		colResDate.setPrefWidth(130);
		colResDate.setStyle("-fx-alignment: CENTER;");
		colResDate.setCellValueFactory(new PropertyValueFactory<>("reservCareDate"));
		
		tvCareList.setItems(careData);
		tvCareList.getColumns().addAll(colCareNum, colDocName, colPatName, colResDate);	
		careTotalList();
	}
	// 맨처음에 의테이블뷰에 데이타베이스 값을 읽어서 테이블뷰에 가져온다.
	public void careTotalList() {
		ArrayList<Care_Management_MainVO> carelist = null;
		Care_ManagementDAO RDAO = new Care_ManagementDAO();
		Care_Management_MainVO cmvo = null;
		carelist = RDAO.getCareTotal();
		if (carelist == null) {
			PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 요망");
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
	// 등록 버튼
	public void handlerBtnSaveAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/care_management_content.fxml"));
			PMClass.callStage(mainView, "진료 기록 상세창", btnClose);
		} catch (Exception e1) {
//			PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
			e1.printStackTrace();
		}
	}
	// 수정 버튼
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
			stageDialog.setTitle("상세정보창");
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
			PMClass.alertDisplay(1, "창 불러오기 실패", "상세정보창 불러오기 오류", e1.toString() + e1.getMessage());
			e1.printStackTrace();
		}
	}
	// 종료 버튼
	public void handlerBtnCloseAction(ActionEvent e) {
		try {
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "관리자 메인창", btnClose);
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "메인 불러오기 실패", "메인창 불러오기 오류", e1.toString() + e1.getMessage());
		}
	}
	//날짜 읽기
	public void handlerDatePickerCareDateAction(ActionEvent e) {
		careData.removeAll(careData);
		ArrayList<Care_Management_MainVO> list = null;
		Care_ManagementDAO CMDAO = new Care_ManagementDAO();
		Care_Management_MainVO cmvo = null;
	    String date=dpCaredate.getValue().toString();
//	    System.out.println(careData.getValue().toString()+"일");
	 
	    list = CMDAO.getCareDateCheck(date);
		
//		 System.out.println( list.get(0).getReservationNumber());
		 
			if (list == null) {
			   PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검요망");
			   return;
			
			} else {
			   for (int i = 0; i < list.size(); i++) {
				cmvo = list.get(i);
				careData.add(cmvo);
			   }
			}
		}
}
