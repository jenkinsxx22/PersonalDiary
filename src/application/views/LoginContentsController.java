package application.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import application.beans.User;
import application.model.Users;
import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginContentsController implements Initializable{
	@FXML
	private BorderPane mainPane;
	@FXML
	private Button btnOK;
	@FXML
	private TextField txtUserName;
	@FXML
	private PasswordField txtPassword;
	private Stage dialogStage;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	  
	@FXML
	private void okLogin(ActionEvent event) {
		
		  File loginfile = new File("logindetails.xml");
		  try {
			JAXBContext logContext = JAXBContext.newInstance(User.class);
			Marshaller logMarshall = logContext.createMarshaller();
			User logBean = new User();
			logBean.setUserName(txtUserName.getText());
			logBean.setPassword(txtPassword.getText());
			logMarshall.marshal(logBean, loginfile);
			
			 Users user = new Users();
			 UserService userservice = new UserService();
			
			user.setEmail(txtUserName.getText());
			userservice.SaveUser(user);
	

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		  
		  try {
				Stage stage = new Stage();
				FXMLLoader loader =new FXMLLoader(getClass().getResource("Home.fxml"));			
				Parent fxml = (Parent) loader.load();
				stage.setScene(new Scene(fxml, 800, 500));
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  dialogStage.close();
		System.out.println("Going to logging into System..!");

	}
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
}
