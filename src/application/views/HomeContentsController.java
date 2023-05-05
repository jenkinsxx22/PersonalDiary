package application.views;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import application.beans.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane; 

public class HomeContentsController implements Initializable{
	@FXML
	private BorderPane mainPane;
	@FXML
	private Label lblHome;
	
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		user = getLoginBean();
		lblHome.setText("Welcome Back "+user.getUserName()+" !");
		
	}
	  
	  
	public void setUser(User user) {
		this.user = user;
		
	}
	
	public User getLoginBean() {
		
		 File loginfile = new File("logindetails.xml");
		 User logBean=new User(); 

		 JAXBContext logContext;
		try {
			logContext = JAXBContext.newInstance(User.class);
			Unmarshaller logUnmarshall = logContext.createUnmarshaller();
			logBean=(User) logUnmarshall.unmarshal(loginfile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return logBean;
			
	}

}
