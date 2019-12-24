package AppMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HospitalMain extends Application {
	public static void main (String [] args) {
			launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//1. ���� Ȩ������ �α��� Login.fxml
		//2. ���θ޴� -> SelectMain.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("LoginTest");
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}

}
