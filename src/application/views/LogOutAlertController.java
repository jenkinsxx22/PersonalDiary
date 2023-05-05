package application.views;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import application.MainApplication;
import application.beans.User;
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
	private MainApplication mainApp;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub 
	}
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	@FXML
	private void okLogout(ActionEvent event) {
		File loginfile = new File("logindetails.xml");
		  try {
			JAXBContext logContext = JAXBContext.newInstance(User.class);
			Marshaller logMarshall = logContext.createMarshaller();
			User logBean = new User();
			logMarshall.marshal(logBean, loginfile);	

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		  
		  System.exit(0);
		System.out.println("Going to logging out from System..!");

	}
	@FXML
	private void CancelOperation(ActionEvent event) {
		
		okClicked = true;
		dialogStage.close();
		System.out.println("Going to clossing out from System..!");

	}
	  
}
