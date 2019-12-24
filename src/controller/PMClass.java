package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PMClass {
	//경고창 띄우기
	public static void alertDisplay(int type, String title, String headerText, String contentText) {
		Alert alert = null;
		switch(type){
		case  1:   alert = new Alert(AlertType.WARNING); break;
		case  2:   alert = new Alert(AlertType.CONFIRMATION);break;
		case  3:   alert = new Alert(AlertType.ERROR);break;
		case  4:   alert = new Alert(AlertType.NONE);break;
		case  5:   alert = new Alert(AlertType.INFORMATION);break;
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.setResizable(false);
		alert.showAndWait();
	}
	public static void callStage(Parent mainView,String title,Button button) {
		Scene scene = new Scene(mainView);
		Stage mainStage=new Stage();
		mainStage.setTitle(title);
		mainStage.setScene(scene);
		mainStage.setResizable(true);
		((Stage)button.getScene().getWindow()).close();
		mainStage.show();
	}
	public static void callStage2(Parent mainView,String title) {
		Scene scene = new Scene(mainView);
		Stage mainStage=new Stage();
		mainStage.setTitle(title);
		mainStage.setScene(scene);
		mainStage.setResizable(true);
		mainStage.show();
	}
	
}
