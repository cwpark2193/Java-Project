package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DoctorVO;
import model.noticeBoardVO;


// ���ǰԽ��� , Ī���Խ���
public class NociteBoardMainController implements Initializable {
	@FXML
	private Button btnDelete; // �����ϱ�
//	@FXML
//	private Button btnReset; // ���ΰ�ħ
//	@FXML
//	private Button btnModify; // �����ϱ�
	@FXML
	private Button btnFinal; // �ۼ��ϱ�
	@FXML
	private RadioButton rdoOpen; // ����
	@FXML
	private RadioButton rdoOFF; // �����
	@FXML
	private TextArea txtArea; // �������°�
	@FXML
	private TextField txtHuman; // �ۼ���
	@FXML
	private TextField txtTitle; // ����
	@FXML
	private PasswordField txtPassword; // ��й�ȣ
	@FXML
	private ComboBox<String> comboBox; // ���ǰԽ��� Ī���Խ��� Clear
	// ���̺�信 �����ֱ� ���� ������
	@FXML
	private TableView<HospitalNo1> tableView;
	ObservableList<HospitalNo1> Tbldata;
//===================================	
	//���̺�並 ���������� ��ġ���� ��ü���� �����Ҽ� �ִ� ���� ����.
	private  int selected;
	private ObservableList<HospitalNo1> selected2;
	
	private boolean edit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ComboboxSetting();
		TableViewSetting();
		ResetContents();
		//���̺� �� Ŭ����
		tableView.setOnMousePressed(e -> {BtnTableViewPrint(e);});
		// �ۼ��ϱ� ��ư
		btnFinal.setOnAction(e -> {handlerBtnFinalAction(e);});
		// �����ϱ� ��ư
		btnDelete.setOnAction(e -> {handlerBtnDeleteAction(e);});
//		 btnModify.setOnAction(e->{handlerBtnModify(e);});

	}
	// 1. �޺��ڽ� �����ϱ�
	public void ComboboxSetting() {
		ObservableList<String> listItem = FXCollections.observableArrayList("�����ϱ�", "Ī���ϱ�");
		comboBox.setItems(listItem);
	}
	// 2. ���̺�� �÷� �����ϱ�.
	public void TableViewSetting() {

		// ���̺� ����
		Tbldata = FXCollections.observableArrayList();
		// ���� �Ұ����ϰ� �ϱ�
		tableView.setDisable(false);
		// 2. ���̺� �÷� �����ϱ�.
		TableColumn colNo1 = new TableColumn("����");
		colNo1.setMinWidth(250);
		colNo1.setStyle("-fx-alignment: CENTER;");
		colNo1.setCellValueFactory(new PropertyValueFactory("No1"));

		TableColumn colNo2 = new TableColumn("����");
		colNo2.setMinWidth(250);
		colNo2.setStyle("-fx-alignment: CENTER;");
		colNo2.setCellValueFactory(new PropertyValueFactory("No2"));

		TableColumn colNo3 = new TableColumn("�ۼ���");
		colNo3.setMinWidth(250);
		colNo3.setStyle("-fx-alignment: CENTER;");
		colNo3.setCellValueFactory(new PropertyValueFactory("No3"));
		// ���̺� �÷� ��ü�� �信 ����Ʈ �߰� �� �׸��߰�.
		tableView.setItems(Tbldata);
		tableView.getColumns().addAll(colNo1, colNo2, colNo3);
	}

	// 3. �ۼ��� ������ ���̺�信 �����Ѵ�.
	public void handlerBtnFinalAction(ActionEvent e) {
		// txtHuman comboBox txtTitle
		try {
			if (txtHuman.getText().equals("") || txtTitle.getText().equals("")) {
				System.out.println("��� �ȵ�");
				throw new Exception();
			} else {
				HospitalNo1 hsp = new HospitalNo1(txtTitle.getText(), comboBox.getSelectionModel().getSelectedItem(),
						txtHuman.getText());
				if(edit==true) {
					Tbldata.remove(selected);
					Tbldata.add(selected, hsp);
					edit=false;
				}else {
					Tbldata.add(hsp);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return ;
		}
		// �ۼ� �Ϸ�� �������θ� �ᱺ��.
//		rdoOpen.setDisable(true);
//		rdoOFF.setDisable(true);
		// �ۼ��� ���� ��й�ȣ �޺��ڽ� �ۼ��ȳ��� �ʱ�ȭ ��Ű�� �Լ� ��
		ResetContents();

	}

	// 4. �ۼ��ϱ� ��ư������ �ۼ��� ���� ��й�ȣ �޺��ڽ� ���� �ʱ�ȭ ��Ű��.
	public void ResetContents() {
		txtArea.clear();
		txtHuman.clear();
		txtTitle.clear();
		txtPassword.clear();
		comboBox.getSelectionModel().clearSelection();
	}

	// 5. ���̺�� ���ý� ������ �������� �Ѵ�.
	public void BtnTableViewPrint(MouseEvent e) {
		try {
			edit = true;
			selected = tableView.getSelectionModel().getFocusedIndex();
			 selected2 = tableView.getSelectionModel().getSelectedItems();
			txtTitle.setText( selected2.get(0).getNo1());
			comboBox.setValue( selected2.get(0).getNo2());
			txtHuman.setText( selected2.get(0).getNo3());
		} catch (Exception e2) {
			System.out.println("Null");
		}
		
	}

	// 6. ���̺�� ������ �����Ѵ�.
	public void handlerBtnDeleteAction(ActionEvent e) {
		Alert alert = new Alert(AlertType.WARNING);
		edit = false;
		Tbldata.remove(selected);
		txtHuman.setDisable(false);
		try {
			txtHuman.clear();
			comboBox.getSelectionModel().getSelectedItem();
			txtTitle.clear();
			alert.setTitle("�˸�");
			alert.setHeaderText("�ش�� ���� �����Ͽ����ϴ�.");
			alert.setContentText("�����Ϸ�");
			alert.showAndWait();

		} catch (Exception e3) {
			alert.setTitle("��� â");
			alert.setHeaderText("������ �߻��Ͽ����ϴ�.");
			alert.setContentText("Error..");
			alert.showAndWait();
			e3.printStackTrace();
		}
	}

	// 7. ���̺�� ������ �����Ѵ�.
	public void handlerBtnModify(ActionEvent e) {
			txtHuman.setDisable(true);
		try {
			Tbldata.remove(selected);
			Tbldata.add(selected , new HospitalNo1(txtHuman.getText(),comboBox.getSelectionModel().getSelectedItem(),txtTitle.getText()));
			txtHuman.setEditable(true);
			comboBox.getSelectionModel().clearSelection();
			txtTitle.setEditable(true);
		}catch(Exception e3) {
			e3.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� â");
			alert.setHeaderText("���� ����");
			alert.setContentText("���� �Ϸ��� ����� ��Ȯ�ϰ� �������ּ���.");
		}
		
	}
	// �� ó���� ���̺�信 ���� �Ѹ���.
	public void docTotalList() {
		ArrayList<noticeBoardVO> nblist = null;
		NoticeBoardDAO nbDAO = new NoticeBoardDAO();
		noticeBoardVO nbVO = null;
//		nblist = nbDAO.getDITotal();
		if (nblist == null) {
			PMClass.alertDisplay(1, "���", "DB �������� ����", "���� ���");
			return;
		}
		for (int i = 0; i < nblist.size(); i++) {
			nbVO = nblist.get(i);
//			Tbldata.add(nbVO);
		}
	}

}

