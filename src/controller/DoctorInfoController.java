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
		// ���� �޺��ڽ� �ʱ�ȭ
		cbDocGen.setItems(FXCollections.observableArrayList("��", "��"));
		// ����ð���
		cbDocTime.setItems(FXCollections.observableArrayList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"));
		// �ǻ��̹����� ��ư�� ������ ��
		btnDocIvSet.setOnAction(e -> handlerBtnDocIvSetAction(e));
		// ��� ��ư�� ������ ��
		btnSave.setOnAction(e -> handlerBtnSaveAction(e));
		// �ʱ�ȭ ��ư�� ������ ��
		btnClear.setOnAction(e -> handlerBtnClearAction(e));
		// ���� ��ư�� ������ ��
		btnClose.setOnAction(e -> handlerBtnCloseAction(e));
		// ���� ����â���� ������ư �������� ��
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
	
	
	// �ǻ��̹����� ��ư�� ������ ��
	public void handlerBtnDocIvSetAction(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
		try {
			selectedFile = fileChooser.showOpenDialog(btnDocIvSet.getScene().getWindow());
			if (selectedFile != null) {
				// �̹��� ���� ���
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

	// ��� ��ư�� ������ ��
	public void handlerBtnSaveAction(ActionEvent e) {
		try {
			File dirMake = new File(dirSave.getAbsolutePath());
			// �̹��� ���� ���� ����
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}
			// �̹��� ���� ����
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
				
				PMClass.alertDisplay(1, "��� ����", "��Ͽ� ��� �Ϸ�", "�����ϼ̽��ϴ�");	
			}
		} catch (Exception e1) {
			PMClass.alertDisplay(1, "��� ����", "�ٽ� �ѹ� �õ��ϼ���", e.toString());
			return;
		}
	}
	// �ʱ�ȭ ��ư�� ������ ��
	public void handlerBtnClearAction(ActionEvent e) {
		fieldInitSetting();
	}
	// ���� ��ư�� ������ ��
	public void handlerBtnCloseAction(ActionEvent e) {
		((Stage) btnClose.getScene().getWindow()).close();
	}
	// �ʵ� �ʱ�ȭ
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
	// �̹��� ����
	public boolean imageDelete(String fileName) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getAbsolutePath() + "\\" + fileName); // �����̹��� ����
			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();
				// �⺻ �̹���
				ivInit(ivDocPhoto);
			}
		} catch (Exception ie) {
			System.out.println("ie = [ " + ie.getMessage() + "]");
			result = false;
		}
		return result;
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
}
