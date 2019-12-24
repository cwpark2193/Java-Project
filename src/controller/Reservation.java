package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.crypto.spec.IvParameterSpec;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Reservation implements Initializable {

	// 1. �¶��� ���Ό�� Ȩ������ ����.
	// 2. ����� / �Ƿ��� ���� ����
	// 3. �ش�Ǵ� ������� �´� xx ���� ���� ��� �� ��¥/�ð� ���� + �������
	@FXML	private Button btnExit; // ������
	@FXML	private Button btnRes; // �����ϱ�.
	@FXML	private TextArea txtArea; // �ؽ�Ʈ �����.
	@FXML	private DatePicker dtePick; // �� ��
	@FXML	private ComboBox<String> SelectDia; // ����ð� ����
	@FXML	private ImageView imageChange; // ���� ,
	@FXML	ListView<String> listView;
	ObservableList<String> list = FXCollections.observableArrayList("09:00", "10:00", "11:00", "13:00", "14:00",
			"15:00", "16:00", "17:00", "18:00");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRes.setOnAction(e -> {
			handlerBtnRes(e);
		});
		btnExit.setOnAction(e -> {
			handlerBtnExit(e);
		});
		SelectDia.setOnAction(e -> {
			handlerSelectDia(e);
		});
		SelectDia.setItems(list);
		// ȭ���� Ŭ���ϸ� �ش�� ������ ã�Ƽ� �����ͼ� ����Ʈ�信 �־��.
		listViewInsert();
	}
	// ȭ���� Ŭ���ϸ� �ش�� ������ ã�Ƽ� �����ͼ� ����Ʈ�信 �־��.
	public void listViewInsert() {
		ObservableList<String> lvData=FXCollections.observableArrayList();
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctorinfo.fxml"));
//		DoctorInfoController docInfoCont = loader.getController();
//		docInfoCont.doctorVO = selectDoctor.get(0);

		lvData.add("������"); lvData.add("������"); lvData.add("�̼���");lvData.add("������");
		listView.setItems(lvData);
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				imageChange
			}
		});
	}

	// �����ϱ� �̺�Ʈ.
	public void handlerBtnExit(ActionEvent e) {
		((Stage) btnExit.getScene().getWindow()).close();
	}

	// �����ϱ� �̺�Ʈ
	public void handlerBtnRes(ActionEvent e) {
		PMClass.alertDisplay(5, "���� �Ϸ�", "������ �Ϸ�Ǿ����ϴ�.", "�ش� ��¥�� �� ���ֽñ� �ٶ��ϴ�.");
		txtArea.clear();
		dtePick.getEditor().clear();
		SelectDia.getSelectionModel().clearSelection();

	}

	// ����Ʈ �̸� ���ý� ���� �ҷ�����
	public void handlerSelectDia(ActionEvent e) {
	}
}
