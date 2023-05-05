package application.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import application.beans.User;
import application.model.Users;
import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser; 

public class ProfileContentsController implements Initializable{
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private TextField textFirstName;
	@FXML
	private TextField textLastName;
	@FXML
	private TextField textEmail;
	@FXML
	private TextField textContact;
	@FXML
	private ImageView imageProfile;
	
	@FXML
	private Button btnSave;
	@FXML
	private Button btnBrowse;
	private User userBean;
	private Boolean updateRow = false;

	  private PreparedStatement store,retrieve;
	  private String storeStatement="UPDATE USERS_2 SET image = ? WHERE EMAIL=?";
	  private String retrieveStatement="SELECT image FROM USERS_2 WHERE EMAIL=?";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 setUserProfileDetails();
		 
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(storeStatement);
			retrieve = conn.prepareStatement(retrieveStatement);
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		  loadImage();
	}
	  
	@FXML
	private void goSave(ActionEvent event) {
		Users user = new Users();
		UserService userservice = new UserService();
		user.setFirstName(textFirstName.getText());
		user.setLastName(textLastName.getText());
		user.setContact(textContact.getText());
		user.setEmail(textEmail.getText());
		
		
		if (updateRow==false) {
		userservice.SaveUser(user);
		System.out.print("Saved here in controlller!");
		}
		else
		{
			user = userservice.GetUserByEmail(userBean.getUserName());
			userservice.UpdateUser(user);
			System.out.print("Updated here in controlller!");
		}
	}
	
	public void setUserProfileDetails() {
		try {
		userBean = getLoginBean();
		if(userBean.getUserName()!=null) {
			Users user = new Users();
			UserService userservice = new UserService();
			
			user = userservice.GetUserByEmail(userBean.getUserName());
			if(user!=null) {
				System.out.println("Here are we together@!");
				String firstname = user.getFirstName();
				
			textFirstName.setText(firstname);
			textLastName.setText(user.getLastName());
			textEmail.setText(user.getEmail());
			textContact.setText(user.getContact());
			if(user.getImage()!=null) {
			  Blob blob = user.getImage();
			  InputStream inputStream = blob.getBinaryStream();
			  Image image = new Image(inputStream);
			  imageProfile.setImage(image);	 
			}
			updateRow   = true;
			}
		}
		}catch(Exception e) {e.printStackTrace();}
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
	
	@FXML
	private void goBrowse(ActionEvent event) {
		chooseFile();
		 loadImage();
	}
	
	  public void chooseFile() {
		  FileChooser fileChooser = new FileChooser();
		  File file = fileChooser.showOpenDialog(btnBrowse.getScene().getWindow());
		  try {
			  FileInputStream fileInputStream = new FileInputStream(file);
			  store.setString(2,  textEmail.getText());
			  store.setBinaryStream(1, fileInputStream, fileInputStream.available());
			  store.execute();
			  Image image = new Image(fileInputStream);
			  imageProfile.setImage(image);
		  }catch(IOException|SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  
	  }
	  
	  public void loadImage() {
		  try {
			retrieve.setString(1, textEmail.getText());
			  ResultSet resultSet = retrieve.executeQuery();
			 if(resultSet.next()) {
				  Blob blob = resultSet.getBlob(1);
				  InputStream inputStream = blob.getBinaryStream();
				  Image image = new Image(inputStream);
				  imageProfile.setImage(image);	  
			  }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
