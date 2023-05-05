package application.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.beans.User;
import application.model.UserContents;
import application.service.UserContentService;
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
	private Button btnCreate;
	@FXML
	private Button btnViewAll;
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
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
			btnHome.setStyle(StyleSheetClicked);
			btnProfile.setStyle(StyleSheetReleased);
			btnCreate.setStyle(StyleSheetReleased);
			btnViewAll.setStyle(StyleSheetReleased);
			try {
				FXMLLoader loader =new FXMLLoader(getClass().getResource("HomeContents.fxml"));			
				Parent fxml = (Parent) loader.load();
				HomeContentsController controller = (HomeContentsController) loader.getController();
				contentPane.getChildren().removeAll();
				contentPane.getChildren().setAll(fxml);
				controller.setUser(user);
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	@FXML
	private void goHome(ActionEvent event) {
		btnHome.setStyle(StyleSheetClicked);
		btnProfile.setStyle(StyleSheetReleased);
		btnCreate.setStyle(StyleSheetReleased);
		btnViewAll.setStyle(StyleSheetReleased);
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("HomeContents.fxml"));			
			Parent fxml = (Parent) loader.load();
			HomeContentsController controller = (HomeContentsController) loader.getController();
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(fxml);
			controller.setUser(user);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
	private void goProfile(ActionEvent event) {
		btnHome.setStyle(StyleSheetReleased);
		btnProfile.setStyle(StyleSheetClicked);
		btnCreate.setStyle(StyleSheetReleased);
		btnViewAll.setStyle(StyleSheetReleased);

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
	@FXML
	private void goCreate(ActionEvent event) {
		btnHome.setStyle(StyleSheetReleased);
		btnProfile.setStyle(StyleSheetReleased);
		btnCreate.setStyle(StyleSheetClicked);
		btnViewAll.setStyle(StyleSheetReleased);
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("CreateContents.fxml"));			
			Parent fxml = (Parent) loader.load();
//			CreateContentsController controller = (CreateContentsController) loader.getController();
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(fxml);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	private void goView(ActionEvent event) {
		btnHome.setStyle(StyleSheetReleased);
		btnProfile.setStyle(StyleSheetReleased);
		btnCreate.setStyle(StyleSheetReleased);
		btnViewAll.setStyle(StyleSheetClicked);
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("ViewContents.fxml"));			
			Parent fxml = (Parent) loader.load();
//			ViewContentsController controller = (ViewContentsController) loader.getController();
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(fxml);
//			controller.setContentList(getUserContents());
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void setUser(User user) {
		this.user=user;
		
	}

	public List<UserContents> getUserContents(String strSearch, int recStart, int recEnd){
		 User userBean = getLoginBean();
		List<UserContents> resList = new ArrayList<UserContents>();
		UserContentService contentservice = new UserContentService();
		resList = contentservice.GetUserContentsByEmailPagination(userBean.getUserName(),strSearch,recStart,recEnd);
		
		resList.forEach(u->{
			System.out.println("[getUserContents] Title: "+u.getTitle() +" Body:"+u.getBody());
		});
		
		return resList;
				
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
