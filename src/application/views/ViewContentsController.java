package application.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import application.beans.User;
import application.model.UserContents;
import application.service.UserContentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane; 

public class ViewContentsController implements Initializable{
	@FXML
	private BorderPane mainPane;
	@FXML
	private GridPane mainGrid; //3x2
//	@FXML
//	private Pagination mainPagination;
	@FXML
	private AnchorPane mainGrid1x1;
	@FXML
	private AnchorPane mainGrid1x2;
	@FXML
	private AnchorPane mainGrid1x3;
	
	@FXML
	private AnchorPane mainGrid2x1;
	@FXML
	private AnchorPane mainGrid2x2;
	@FXML
	private AnchorPane mainGrid2x3;

	@FXML
	private TextField textSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnNext;
	@FXML
	private Button btnPrevious;
	
	private int Counter, totRecords, startIndex, endIndex;
	
	private List<UserContents> resList;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Counter=0;
		refreshPagination();
		btnSearch.setOnAction(event->{
			refreshPagination();
		});
	}
	  
	
	public void setContentList(List<UserContents> contentList){
		this.resList=contentList;
	}
	@FXML
	private void goRun(ActionEvent event) {
		refreshPagination();
		
	}
	
	@FXML
	private void goNext(ActionEvent event) {
		refreshPagination();
		
	}
	@FXML
	private void goPrevious(ActionEvent event) {
		refreshPagination();
		
	}
	public void refreshPagination() {
		
		totRecords=getTotalRecordsCount();
		if(totRecords==0) {
			btnNext.setVisible(false);
			btnPrevious.setVisible(false);
		}else
		{
			btnNext.setVisible(true);
			btnPrevious.setVisible(true);
		}
		fillGridAll();
	}
	public void fillGridAll() {
		resList =  getUserContents(textSearch.getText(),startIndex, endIndex);
		mainGrid1x1.getChildren().clear();
		mainGrid1x2.getChildren().clear();
		mainGrid1x3.getChildren().clear();
		mainGrid2x1.getChildren().clear();
		mainGrid2x2.getChildren().clear();
		mainGrid2x3.getChildren().clear();
//
//		mainGrid.getChildren().clear();
		Counter=0;

		resList.forEach(card->{
			Counter+=1;
			if(Counter==1) {
				fillGrid(mainGrid1x1,card);
			}
			if(Counter==2) {
				fillGrid(mainGrid1x2,card);
			}
			if(Counter==3) {
				fillGrid(mainGrid1x3,card);
			}
			if(Counter==4) {
				fillGrid(mainGrid2x1,card);
			}

			if(Counter==5) {
				fillGrid(mainGrid2x2,card);
			}

			if(Counter==6) {
				fillGrid(mainGrid2x3,card);
			}

		});
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

	public void fillGrid(AnchorPane anchor, UserContents content) {
		try {

			FXMLLoader loader =new FXMLLoader(getClass().getResource("ViewContentsCard.fxml"));			
			Parent fxml = (Parent) loader.load();
			ViewContentsCardController controller = (ViewContentsCardController) loader.getController();
//			anchor.getChildren().removeAll();
			anchor.getChildren().setAll(fxml);
//			anchor.getChildren().addAll(mainPagination);
			controller.setContent(content);
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public int getTotalRecordsCount() {
		 User userBean = getLoginBean();
		UserContentService contentservice = new UserContentService();
		int totalrecords=contentservice.GetCountUserContentsByEmail(userBean.getUserName());
		return totalrecords;
	}
}
