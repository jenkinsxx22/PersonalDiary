package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage; 

public class LogOutAlertController implements Initializable{
	@FXML
	private BorderPane mainPane;
	@FXML
	private Button btnOK;
	@FXML
	private Button btnCancel;

	private boolean okClicked = false;
	private Stage dialogStage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub 
	}
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	@FXML
	private void okLogout(ActionEvent event) {
		
		System.out.println("Going to logging out from System..!");

	}
	@FXML
	private void CancelOperation(ActionEvent event) {
		
		okClicked = true;
		dialogStage.close();
		System.out.println("Going to clossing out from System..!");

	}
	  
}
