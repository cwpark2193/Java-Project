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
	private String localUrl = ""; // 이미지 파일 경로
	private Image localImage;
	private File selectedFile = null;
	public DoctorVO doctorVO=null;
	public PatientVO patientVO=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 테이블 설정
		tvResSetin();
		// 날짜 설정
		dpResDate.setOnAction(e -> handlerDatePickerResDateAction(e));
		// 상세정보 창 액션
		btnPersonInfo.setOnAction(e -> handlerBtnPIAction(e));
		// 예약취소 버튼
		btnClear.setOnAction(e -> handlerBtnClearAction(e));
		// 새로고침
		btnResRef.setOnAction(e -> handlerBtnResRefAction(e));
		// 종료 버튼 액션
		btnResClose.setOnAction(e -> handlerBtnResCloseAction(e));
		// 테이블 뷰 클릭
		tvResList.setOnMousePressed(e -> handlerResListTableViewPressedAction(e));
	}
	// 새로고침
	public void handlerBtnResRefAction(ActionEvent e) {
		resTotalList();
	}
	// 테이블 설정
	public void tvResSetin() {
		// 테이블설정 arraylist 설정
		resData = FXCollections.observableArrayList();
		// tableview 를 편집못하게 설정
		tvResList.setEditable(false);
		
		TableColumn colResNum = new TableColumn("예약번호");
		colResNum.setPrefWidth(80);
		colResNum.setStyle("-fx-alignment: CENTER;");
		colResNum.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
		
		TableColumn colDocName = new TableColumn("의사 이름");
		colDocName.setPrefWidth(90);
		colDocName.setStyle("-fx-alignment: CENTER;");
		colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
		
		TableColumn colDocPhone = new TableColumn("의사 연락처");
		colDocPhone.setPrefWidth(150);
		colDocPhone.setStyle("-fx-alignment: CENTER;");
		colDocPhone.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));
		
		
		TableColumn colPatName = new TableColumn("환자 이름");
		colPatName.setPrefWidth(90);
		colPatName.setStyle("-fx-alignment: CENTER;");
		colPatName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
		
		TableColumn colPatPhone = new TableColumn("환자 연락처");
		colPatPhone.setPrefWidth(150);
		colPatPhone.setStyle("-fx-alignment: CENTER;");
		colPatPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));
		
		TableColumn colResDate = new TableColumn("예약 날짜");
		colResDate.setPrefWidth(120);
		colResDate.setStyle("-fx-alignment: CENTER;");
		colResDate.setCellValueFactory(new PropertyValueFactory<>("reservCareDate"));
		
		TableColumn colResTime = new TableColumn("예약 시간");
		colResTime.setPrefWidth(90);
		colResTime.setStyle("-fx-alignment: CENTER;");
		colResTime.setCellValueFactory(new PropertyValueFactory<>("reservCaretime"));
		
		TableColumn colResReas = new TableColumn("예약 사유");
		colResReas.setPrefWidth(220);
		colResReas.setStyle("-fx-alignment: CENTER;");
		colResReas.setCellValueFactory(new PropertyValueFactory<>("reservReaseon"));
		
		tvResList.setItems(resData);
		tvResList.getColumns().addAll(colResNum, colDocName, colDocPhone, colPatName, colPatPhone, colResDate, colResTime, colResReas);	
		resTotalList();
	}
	// 이번달 예약 버튼
	public void handlerBtnMRCction(ActionEvent e) {
	
	}
	// 테이블 뷰 클릭
	public void handlerResListTableViewPressedAction(MouseEvent e) {
		// 눌렀을떄 위치와 해당된 객체를 가져온다.
		selectedDocIndex = tvResList.getSelectionModel().getSelectedIndex();
		selectResvation = tvResList.getSelectionModel().getSelectedItems();
	}
	
	//날짜 읽기
	public void handlerDatePickerResDateAction(ActionEvent e) {
		resData.removeAll(resData);
		ArrayList<ReservationVO> list = null;
		ReservationDAO RDAO = new ReservationDAO();
		ReservationVO rvo = null;
	    String date=dpResDate.getValue().toString();
//	    System.out.println(dpResDate.getValue().toString()+"일");
	 
	    list = RDAO.getResDateCheck(date);
	
//	 System.out.println( list.get(0).getReservationNumber());
	 
		if (list == null) {
		   PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검요망");
		   return;
		
		} else {
		   for (int i = 0; i < list.size(); i++) {
		   	rvo = list.get(i);
		   	resData.add(rvo);
		   }
		}
	}
	// 예약취소 버튼
	public void handlerBtnClearAction(ActionEvent e) {
		try {
			selectResvation = tvResList.getSelectionModel().getSelectedItems();
			ReservationDAO RDAO = new ReservationDAO();
			RDAO.getResDelete(selectResvation.get(0).getReservationNumber());
			resData.removeAll(resData);
			resTotalList();
		} catch (Exception e1) {
//			PMClass.alertDisplay(1, "삭제 오류", "8번. 삭제 실패", e1.toString());
			e1.printStackTrace();
		}
	}
	//환자 정보 창
	public void handlerBtnPIAction(ActionEvent e) {
		try {
			Parent PIView = FXMLLoader.load(getClass().getResource("/view/personidboard.fxml"));
			PMClass.callStage2(PIView, "개인 정보 창");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//의사 정보 창
	public void handlerBtnDIAction(ActionEvent e) {
		try {	
			Parent docInfoView=FXMLLoader.load(getClass().getResource("/view/doctorinfo.fxml"));
			PMClass.callStage2(docInfoView, "상세정보창");
		}catch(Exception e1) {
			PMClass.alertDisplay(1,"창 불러오기 실패","상세정보창 불러오기 오류",e1.toString()+e1.getMessage());
		}
	}
	//종료 버튼 액션
	public void handlerBtnResCloseAction(ActionEvent e) {
		try {	
			Parent mainView=FXMLLoader.load(getClass().getResource("/view/mainboard.fxml"));
			PMClass.callStage(mainView, "관리자 메인창", btnResClose);
		}catch(Exception e1) {
			PMClass.alertDisplay(1,"메인 불러오기 실패","메인창 불러오기 오류",e1.toString()+e1.getMessage());
		}
	}
	
	// 맨처음에 의테이블뷰에 데이타베이스 값을 읽어서 테이블뷰에 가져온다.
	public void resTotalList() {
		resData.removeAll(resData);
		ArrayList<ReservationVO> reslist = null;
		ReservationDAO RDAO = new ReservationDAO();
		ReservationVO rvo = null;
		reslist = RDAO.getResTotal();
		if (reslist == null) {
			PMClass.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 요망");
			return;
		}
		for (int i = 0; i < reslist.size(); i++) {
			rvo = reslist.get(i);
			resData.add(rvo);
		}
	}
}
