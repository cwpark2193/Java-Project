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
import model.PatientVO;

public class PatientInfoController implements Initializable {
	@FXML TextField tfPatName;
	@FXML TextField tfPatAdd;
	@FXML TextField tfPatPhone;
	@FXML TextField tfPatSsn;
	@FXML TextField tfPatAge;
	@FXML TextField tfPatID;
	@FXML ImageView ivPatImage;
	@FXML PasswordField pfPatPass;
	@FXML ComboBox<String> cbPatGen;
	@FXML Button btnSave;
	@FXML Button btnClear;
	@FXML Button btnImageSave;
	@FXML Button btnPIClose;
	private boolean editDelete = false;
	private String localUrl = "";
	private Image localImage;
	private String selectFileName = "";
	private File selectedFile = null;
	private File dirSave = new File("D:/images");
	private PatientDAO PIDAO;
	ObservableList<PatientVO> patData;
	public int state=0;
	public int selectId=0;
	public PatientVO patientVO=null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//콤보박스 세팅
		cbPatGen.setItems(FXCollections.observableArrayList("남", "여"));
		// 이미지 버튼을 눌렀을 때
		btnImageSave.setOnAction(e -> handlerBtnPatIvSetAction(e));
		// 등록 버튼을 눌렀을 때
		btnSave.setOnAction(e -> handlerBtnSaveAction(e));
		// 초기화 버튼을 눌렀을 때
		btnClear.setOnAction(e -> handlerBtnClearAction(e));
		// 종료 버튼을 눌렀을 때
		btnPIClose.setOnAction(e -> handlerBtnPICloseAction(e));
		// 정보 메인창에서 수정버튼 눌러졌을 때
		try {
			if(state == 1) {
				tfPatID.setText(patientVO.getPatientID());
				pfPatPass.setText(patientVO.getPatientPassword());
				tfPatName.setText(patientVO.getPatientName());
				cbPatGen.setPromptText(patientVO.getPatientGender());
				tfPatAge.setText(String.valueOf(patientVO.getPatientAge()));
				tfPatSsn.setText(patientVO.getPatientSSN());
				tfPatPhone.setText(patientVO.getPatientPhone());
				tfPatAdd.setText(patientVO.getPatientAddress());
				
				String fileName = patientVO.getPatientImage();
				File selectedFile = new File("D:/images/" + fileName);
				localUrl = selectedFile.toURI().toURL().toString();
				localImage = new Image(localUrl, false);
				ivPatImage.setImage(localImage);
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
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
				PatientVO pvo = null;
				if(selectedFile !=null) {
					imageDelete(patientVO.getPatientImage());
					String fileName = imageSave(selectedFile);
					pvo = new PatientVO(tfPatID.getText(), pfPatPass.getText(), tfPatName.getText(),
							cbPatGen.getValue(), Integer.parseInt(tfPatAge.getText().trim()), tfPatSsn.getText(),
							tfPatPhone.getText(), tfPatAdd.getText(), fileName);
				}else {				
					pvo = new PatientVO(tfPatID.getText(), pfPatPass.getText(), tfPatName.getText(),
							cbPatGen.getValue(), Integer.parseInt(tfPatAge.getText().trim()), tfPatSsn.getText(),
							tfPatPhone.getText(), tfPatAdd.getText(), patientVO.getPatientImage()); 
				}
				PatientDAO PDAO = new PatientDAO();
				PDAO.getPatUpdate(pvo, patientVO.getPatientNumber());
				
			}else {
				String fileName = imageSave(selectedFile);
				PatientVO pvo = new PatientVO(tfPatID.getText(), pfPatPass.getText(), tfPatName.getText(),
						cbPatGen.getValue(), Integer.parseInt(tfPatAge.getText().trim()), tfPatSsn.getText(),
						tfPatPhone.getText(), tfPatAdd.getText(), fileName);
				PatientDAO PDAO = new PatientDAO();
				PDAO.getPatientInfoRegiste(pvo);		
				
				PMClass.alertDisplay(1, "등록 성공", "목록에 등록 완료", "수고하셨습니다");	
			}
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "등록 실패", "다시 한번 시도하세요", e.toString());
			return;
		}
	}
	// 초기화 버튼을 눌렀을 때
	public void handlerBtnClearAction(ActionEvent e) {
		tfPatID.clear();
		cbPatGen.getSelectionModel().clearSelection();
		pfPatPass.clear();
		tfPatName.clear();
		tfPatAge.clear();
		tfPatSsn.clear();
		tfPatPhone.clear();
		tfPatAdd.clear();
		localUrl = "/images/profilStandard.png";
		localImage = new Image(localUrl, false);
		ivPatImage.setImage(localImage);
	}
	// 이미지 버튼을 눌렀을 때
	public void handlerBtnPatIvSetAction(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
		try {
			selectedFile = fileChooser.showOpenDialog(btnImageSave.getScene().getWindow());
			if (selectedFile != null) {
				// 이미지 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		localImage = new Image(localUrl, false);
		ivPatImage.setImage(localImage);
		ivPatImage.setFitHeight(100);
		ivPatImage.setFitWidth(100);
		btnImageSave.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	}
	// 종료 버튼을 눌렀을 때
	public void handlerBtnPICloseAction(ActionEvent e) {
		((Stage)btnPIClose.getScene().getWindow()).close();
	}
	// 이미지 저장
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// 이미지 파일명 생성
			fileName = "patientInfo" + System.currentTimeMillis() + "_" + file.getName();
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
				ivInit(ivPatImage);
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
