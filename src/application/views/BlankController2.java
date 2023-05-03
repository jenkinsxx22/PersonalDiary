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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class BlankController2 implements Initializable{
	  @FXML
	    private GridPane gridPane;
	  @FXML
	    private Button load,open,next,prev;
	  @FXML
	  private Label counter;
	  @FXML
	  	private ImageView imageView;
	    
	  private PreparedStatement store,retrieve;
	  private String storeStatement="INSERT INTO photos(image) VALUES(?)";
	  private String retrieveStatement="SELECT image FROM photos WHERE ID=?";
	  private int count;
	  @Override
	  
	    public void initialize(URL location, ResourceBundle resources) {
		  count=1;
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(storeStatement);
			retrieve = conn.prepareStatement(retrieveStatement);
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  load.setOnAction(e->{loadImage(); counter.setText(String.valueOf(count));});
		  open.setOnAction(e->{chooseFile();counter.setText(String.valueOf(count));});
		  next.setOnAction(e->{loadImageNext();counter.setText(String.valueOf(count));});
		  prev.setOnAction(e->{loadImageprev();counter.setText(String.valueOf(count));});
			
	  }
	  
	  
	  public void chooseFile() {
		  FileChooser fileChooser = new FileChooser();
		  File file = fileChooser.showOpenDialog(open.getScene().getWindow());
		  try {
			  FileInputStream fileInputStream = new FileInputStream(file);
			  store.setBinaryStream(1, fileInputStream, fileInputStream.available());
			  store.execute();
			  Image image = new Image(fileInputStream);
			  imageView.setImage(image);
		  }catch(IOException|SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  
	  }
	  public void loadImage() {
		  count=1;
		  	
		  try {
			retrieve.setInt(1, count);
			  ResultSet resultSet = retrieve.executeQuery();
			 if(resultSet.next()) {
				  Blob blob = resultSet.getBlob(1);
				  InputStream inputStream = blob.getBinaryStream();
				  Image image = new Image(inputStream);
				  imageView.setImage(image);	  
			  }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  public void loadImageNext() {	  
		  	
		  try {
			retrieve.setInt(1, count);
			  ResultSet resultSet = retrieve.executeQuery();
			 if(resultSet.next()) {
				 count+=1;
				  Blob blob = resultSet.getBlob(1);
				  InputStream inputStream = blob.getBinaryStream();
				  Image image = new Image(inputStream);
				  imageView.setImage(image);	  
			  }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  public void loadImageprev() {
		  if(count>0) {
				 count-=1;			  
		  }
		  	
		  try {
			retrieve.setInt(1, count);
			  ResultSet resultSet = retrieve.executeQuery();
			 if(resultSet.next()) {
				  Blob blob = resultSet.getBlob(1);
				  InputStream inputStream = blob.getBinaryStream();
				  Image image = new Image(inputStream);
				  imageView.setImage(image);	  
			  }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	    
}
