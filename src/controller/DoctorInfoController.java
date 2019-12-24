package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.DoctorVO;

public class DoctorInfoController implements Initializable {
	@FXML
	ComboBox<String> cbDocGen;
	@FXML
	ComboBox<String> cbDocTime;
	@FXML
	TextField tfDocID;
	@FXML
	TextField tfDocName;
	@FXML
	TextField tfDocAge;
	@FXML
	TextField tfDocSsn;
	@FXML
	TextField tfDocPhone;
	@FXML
	TextField tfDocAdd;
	@FXML
	TextField tfDocLN;
	@FXML
	ImageView ivDocPhoto;
	@FXML
	PasswordField pfDocPass;
	@FXML
	Button btnDocIvSet;
	@FXML
	Button btnDLPSet;
	@FXML
	Button btnSave;
	@FXML
	Button btnClear;
	@FXML
	Button btnClose;

	private String localUrl = "";
	private Image localImage;
	private String selectFileName = "";
	private File selectedFile = null;
	private File dirSave = new File("D:/images");
	private DoctorInfoDAO DIDAO;
	private ObservableList<DoctorVO> docData;
	public int state=0;
	public int selectId=0;
	public DoctorVO doctorVO=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 성별 콤보박스 초기화
		cbDocGen.setItems(FXCollections.observableArrayList("남", "여"));
		// 진료시간대
		cbDocTime.setItems(FXCollections.observableArrayList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"));
		// 의사이미지쪽 버튼을 눌렀을 때
		btnDocIvSet.setOnAction(e -> handlerBtnDocIvSetAction(e));
		// 등록 버튼을 눌렀을 때
		btnSave.setOnAction(e -> handlerBtnSaveAction(e));
		// 초기화 버튼을 눌렀을 때
		btnClear.setOnAction(e -> handlerBtnClearAction(e));
		// 종료 버튼을 눌렀을 때
		btnClose.setOnAction(e -> handlerBtnCloseAction(e));
		// 정보 메인창에서 수정버튼 눌러졌을 때
		try {
			if(state == 1) {
				tfDocID.setText(doctorVO.getDoctorID());
				pfDocPass.setText(doctorVO.getDoctorPassword());
				tfDocName.setText(doctorVO.getDoctorName());
				cbDocGen.setPromptText(doctorVO.getDoctorGender());
				tfDocAge.setText(String.valueOf(doctorVO.getDoctorAge()));
				tfDocSsn.setText(doctorVO.getDoctorSSN());
				tfDocPhone.setText(doctorVO.getDoctorPhone());
				tfDocAdd.setText(doctorVO.getDoctorAddress());
				cbDocTime.setPromptText(doctorVO.getDoctorCareTime());
				tfDocLN.setText(doctorVO.getDoctorLicense());
				
				String fileName = doctorVO.getDoctorImage();
				File selectedFile = new File("D:/images/" + fileName);
				localUrl = selectedFile.toURI().toURL().toString();
				localImage = new Image(localUrl, false);
				ivDocPhoto.setImage(localImage);
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	// 의사이미지쪽 버튼을 눌렀을 때
	public void handlerBtnDocIvSetAction(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
		try {
			selectedFile = fileChooser.showOpenDialog(btnDocIvSet.getScene().getWindow());
			if (selectedFile != null) {
				// 이미지 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		localImage = new Image(localUrl, false);
		ivDocPhoto.setImage(localImage);
		ivDocPhoto.setFitHeight(130);
		ivDocPhoto.setFitWidth(130);
		btnDocIvSet.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	}

	// 등록 버튼을 눌렀을 때
	public void handlerBtnSaveAction(ActionEvent e) {
		try {
			File dirMake = new File(dirSave.getAbsolutePath());
			// 이미지 저장 폴더 생성
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}
			// 이미지 파일 저장
			if(state==1) {
				DoctorVO dvo = null;
				if(selectedFile !=null) {
					imageDelete(doctorVO.getDoctorImage());
					String fileName = imageSave(selectedFile);
					dvo = new DoctorVO(tfDocID.getText(), pfDocPass.getText(), tfDocName.getText(),
							cbDocGen.getValue(), Integer.parseInt(tfDocAge.getText().trim()), tfDocSsn.getText(),
							tfDocPhone.getText(), tfDocAdd.getText(), cbDocTime.getValue(), tfDocLN.getText(), fileName);
				}else {				
					dvo = new DoctorVO(tfDocID.getText(), pfDocPass.getText(), tfDocName.getText(),
							cbDocGen.getValue(), Integer.parseInt(tfDocAge.getText().trim()), tfDocSsn.getText(),
							tfDocPhone.getText(), tfDocAdd.getText(), cbDocTime.getValue(), tfDocLN.getText(), doctorVO.getDoctorImage());
				}
				DoctorInfoDAO DIDAO = new DoctorInfoDAO();
				DIDAO.getDoctorUpdate(dvo, doctorVO.getDoctorNumber());
				
			}else {
				String fileName = imageSave(selectedFile);
				DoctorVO dvo = new DoctorVO(tfDocID.getText(), pfDocPass.getText(), tfDocName.getText(),
						cbDocGen.getValue(), Integer.parseInt(tfDocAge.getText().trim()), tfDocSsn.getText(),
						tfDocPhone.getText(), tfDocAdd.getText(), cbDocTime.getValue(), tfDocLN.getText(), fileName);
				DoctorInfoDAO DIDAO = new DoctorInfoDAO();
				DIDAO.getDoctorInfoRegiste(dvo);		
				
				PMClass.alertDisplay(1, "등록 성공", "목록에 등록 완료", "수고하셨습니다");	
			}
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "등록 실패", "다시 한번 시도하세요", e.toString());
			return;
		}
	}
	// 초기화 버튼을 눌렀을 때
	public void handlerBtnClearAction(ActionEvent e) {
		fieldInitSetting();
	}
	// 종료 버튼을 눌렀을 때
	public void handlerBtnCloseAction(ActionEvent e) {
		((Stage) btnClose.getScene().getWindow()).close();
	}
	// 필드 초기화
	public void fieldInitSetting() {
		tfDocID.clear();
		pfDocPass.clear();
		tfDocName.clear();
		cbDocGen.getSelectionModel().clearSelection();
		tfDocAge.clear();
		tfDocSsn.clear();
		tfDocPhone.clear();
		tfDocAdd.clear();
		cbDocTime.getSelectionModel().clearSelection();
		tfDocLN.clear();
		localUrl = "/images/profilStandard.png";
		localImage = new Image(localUrl, false);
		ivDocPhoto.setImage(localImage);
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
	// 이미지 삭제
	public boolean imageDelete(String fileName) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getAbsolutePath() + "\\" + fileName); // 삭제이미지 파일
			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();
				// 기본 이미지
				ivInit(ivDocPhoto);
			}
		} catch (Exception ie) {
			System.out.println("ie = [ " + ie.getMessage() + "]");
			result = false;
		}
		return result;
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
}
