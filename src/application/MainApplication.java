package application;


import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import application.beans.User;
import application.views.HomeContentsController;
import application.views.HomeController;
import application.views.LoginContentsController;
import application.views.ProfileContentsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle; 

public class MainApplication extends Application{
	private User logBean;
	    @Override
	    public void start(Stage primaryStage) {

//    		Parent root = FXMLLoader.load(getClass().getResource("views/Home.fxml"));	    		
//	        primaryStage.setScene(new Scene(root, 800, 500));
//	        primaryStage.initStyle(StageStyle.UNDECORATED);
//	        primaryStage.show();
	    	logBean = getLoginBean();
	    	
	    	if(logBean.getUserName()!=null) {	    		
	    		showHome(primaryStage,logBean);	
	    	}
	    	else {
	    		showLogin(primaryStage);
	    	}
	    }
	    public MainApplication() {
	    	
	    }

	    public static void main(String[] args) {
	        launch(args);
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
		
		public void showHome(Stage primaryStage, User user) {
			
    		FXMLLoader loader =new FXMLLoader(getClass().getResource("views/Home.fxml"));			
				Parent fxml;
				try {
					fxml = (Parent) loader.load();
			        primaryStage.initStyle(StageStyle.UNDECORATED);
			        primaryStage.setScene(new Scene(fxml, 800, 600));
			        primaryStage.show();
			        HomeController controller = (HomeController) loader.getController();
			        controller.setUser(user);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		public void showLogin(Stage primaryStage) {
			try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("views/LoginContents.fxml"));			
			Parent fxml = (Parent) loader.load();
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.setScene(new Scene(fxml, 493, 258));
	        primaryStage.show();
			LoginContentsController controller = (LoginContentsController) loader.getController();
			controller.setDialogStage(primaryStage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
