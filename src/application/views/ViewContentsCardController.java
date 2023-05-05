package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.UserContents;
import application.util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane; 

public class ViewContentsCardController implements Initializable{
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblBody;
	@FXML
	private Label lblDate;
	@FXML
	private Button btnEdit;
	
	private UserContents userContent;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	  
	public void setContent(UserContents usercontent) {
		this.userContent=usercontent;
		System.out.println("[setContent] Title:"+userContent.getTitle()+" Body:"+userContent.getBody());
		lblTitle.setText(userContent.getTitle());
		lblBody.setText(userContent.getBody());
		lblDate.setText(DateUtil.format(userContent.getCreatedOn()));

	}
	@FXML
	private void goEdit(ActionEvent event) {
		
	}
	  

}
