package application.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle; 

public class HomeController implements Initializable{
	
	@FXML
	private BorderPane mainPane;
	@FXML
	private AnchorPane contentPane;
	
	
	@FXML
	private Button btnHome;
	@FXML
	private Button btnProfile;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnClose;
	@FXML
	private MenuButton btnSearch;
	private String StyleSheetClicked= 
			"	-fx-background-radius: 0px;\r\n" + 
			"	-fx-border-color: #B20837;\r\n" + 
			"	-fx-border-width: 0px 0px 4px 0px;\r\n" + 
			"	-fx-border-style: solid;";
	private String  StyleSheetReleased="-fx-background-color: transparent;\r\n" + 
			"	-fx-background-radius: 0px;";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	  
	@FXML
	private void goHome(ActionEvent event) {
		btnHome.setStyle(StyleSheetClicked);
		btnProfile.setStyle(StyleSheetReleased);
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("HomeContents.fxml"));			
			Parent fxml = (Parent) loader.load();
			HomeContentsController controller = (HomeContentsController) loader.getController();
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(fxml);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
	private void goProfile(ActionEvent event) {
		btnHome.setStyle(StyleSheetReleased);
		btnProfile.setStyle(StyleSheetClicked);

		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("ProfileContents.fxml"));			
			Parent fxml = (Parent) loader.load();
			ProfileContentsController controller = (ProfileContentsController) loader.getController();
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(fxml);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}

	@FXML
	private void goLogout(ActionEvent event) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader =new FXMLLoader(getClass().getResource("LogoutAlert.fxml"));			
			Parent fxml = (Parent) loader.load();
			stage.setScene(new Scene(fxml, 441, 192));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			LogOutAlertController controller = (LogOutAlertController) loader.getController();
			 controller.setDialogStage(stage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void goClose(ActionEvent event) {
		System.exit(0);
	}
	@FXML
	private void goShowActivities(ActionEvent event) {
		
	}

}
