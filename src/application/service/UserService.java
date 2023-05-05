package application.service;


import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import application.model.Users;
import javafx.scene.image.Image;

public class UserService {


	  private PreparedStatement store,retrieve;
	public void SaveUser(Users user) {
		String  insertUser="INSERT INTO USERS_2(FIRSTNAME, LASTNAME, CONTACT, EMAIL) "
				+ " VALUES (?,?,?,?)";
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(insertUser);
			store.setString(1, user.getFirstName());
			store.setString(2, user.getLastName());
			store.setString(3, user.getContact());
			store.setString(4, user.getEmail());
			store.execute();
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateUser(Users user) {
		String  updateUser="UPDATE USERS_2 SET FIRSTNAME = ? , LASTNAME=?, CONTACT=?, IMAGE=? WHERE  EMAIL=?";
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(updateUser);
			store.setString(1, user.getFirstName());
			store.setString(2, user.getLastName());
			store.setString(3, user.getContact());
			store.setBlob(4, user.getImage());
			store.setString(5, user.getEmail());
			store.execute();
			System.out.print("Updated here in service!");
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void DeleteUser(int id) {
		String  deleteUser="DELETE FROM  USERS_2 WHERE ID="+id;

		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(deleteUser);
			store.execute();
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Users GetUserById(String id) {
		Users user = new Users();
		
		String  selectUser="SELECT FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS_2 WHERE ID="+id;


		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			retrieve = conn.prepareStatement(selectUser);
			
			 retrieve.setString(1, id);
			  ResultSet res = retrieve.executeQuery();
			 if(res.next()) {
					
					String Id = res.getString("ID");
					String firstname = res.getString("FIRSTNAME");
					String lastname = res.getString("LASTNAME");
					String email = res.getString("EMAIL");
					String contact = res.getString("CONTACT");
//					Blob image = res.getBlob("IMAGE");
					
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setEmail(email);
					user.setContact(contact);
					user.setId(id);
//					user.setImage(image);
					user.setId(Id);
					System.out.println("User gotted! with id "+id);
					System.out.println("User gotted! with id "+Id);
					
		}} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public Users GetUserByEmail(String id) {
		Users user = new Users();
		
		String  selectUser="SELECT ID, FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS_2 WHERE EMAIL=?";


		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			retrieve = conn.prepareStatement(selectUser);
			
			 retrieve.setString(1, id);
			  ResultSet res = retrieve.executeQuery();
			 if(res.next()) {
					
					String Id = res.getString("ID");
					String firstname = res.getString("FIRSTNAME");
					String lastname = res.getString("LASTNAME");
					String email = res.getString("EMAIL");
					String contact = res.getString("CONTACT");
					Blob image = res.getBlob("IMAGE");
					
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setEmail(email);
					user.setContact(contact);
					user.setId(id);
					user.setImage(image);
					user.setId(Id);
					System.out.println("User gotted! with id "+id);
					System.out.println("User gotted! with id "+Id);
					
		}} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return user;
	}
	
	public List<Users> GetAllUsers(){
		
		List<Users> userlist = new ArrayList<Users>();

		String  selectUser="SELECT ID, FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS_2";

		Users user = new Users();

		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			retrieve = conn.prepareStatement(selectUser);
			 
			  ResultSet res = retrieve.executeQuery();
			 if(res.next()) {
					
					String Id = res.getString("ID");
					String firstname = res.getString("FIRSTNAME");
					String lastname = res.getString("LASTNAME");
					String email = res.getString("EMAIL");
					String contact = res.getString("CONTACT");
//					Blob image = res.getBlob("IMAGE");
					
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setEmail(email);
					user.setContact(contact);
					user.setId(Id);
//					user.setImage(image);
					user.setId(Id);
					System.out.println("User gotted! with id "+Id);
					
		}} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return userlist;
	}
	
}
