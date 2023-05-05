package application.views;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import application.beans.User;
import application.model.UserContents;
import application.model.Users;
import application.service.UserContentService;
import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane; 

public class CreateContentsController implements Initializable{
	@FXML
	private BorderPane mainPane;
	@FXML
	private TextField textUserName;
	@FXML
	private TextField textTitle;
	@FXML
	private TextField textBody;
	@FXML
	private TextField textCreatedOn;
	
	@FXML
	private Button btnSave;
	private Boolean updateRow = false;

	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		user = getLoginBean();
		textUserName.setText(user.getUserName());
	}
	  
	@FXML
	private void goSave(ActionEvent event) {
		UserContents userContents = new UserContents();
		UserContentService userContentsservice = new UserContentService();
		userContents.setUserName(textUserName.getText());
		userContents.setTitle(textTitle.getText());
		userContents.setBody(textBody.getText());
		
		if (updateRow==false) {
			userContentsservice.SaveUser(userContents);
		System.out.print("Saved here in controlller!");
		}
		else
		{
			userContents = userContentsservice.GetUserById("1");
			userContentsservice.UpdateUser(userContents);
			System.out.print("Updated here in controlller!");
		}
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
