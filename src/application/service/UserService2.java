package application.service;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import application.model.Users;

public class UserService2 {

	private String dbURL="jdbc:sqlite:users.db";

	private String createTable="CREATE TABLE USERS("
			+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
			+ "FIRSTNAME TEXT,\n"
			+ "LASTNAME TEXT,\n"
			+ "CONTACT TEXT,\n"
			+ "EMAIL TEXT,\n"
			+ "IMAGE BLOB"
			+ ");";
	 
	public void SaveUser(Users user) {
		String  insertUser="INSERT INTO USERS(FIRSTNAME, LASTNAME, CONTACT, EMAIL,IMAGE) VALUES ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getContact()+"','"+user.getEmail()+"','"+user.getImage()+"');";

		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			stmt.execute(insertUser);	
			System.out.println("User saved!");
		}catch(SQLException sqle) {	CreateTable(); sqle.printStackTrace();}
		
	}
	
	public void UpdateUser(Users user) {
		String  updateUser="UPDATE USERS SET FIRSTNAME = '"+user.getFirstName()+"' , LASTNAME='"+user.getLastName()+"', CONTACT='"+user.getContact()+"', IMAGE='"+user.getImage()+"' WHERE  EMAIL='"+user.getEmail()+"'";

		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			stmt.execute(updateUser);	
			System.out.println("User updated!");
		}catch(SQLException sqle) { sqle.printStackTrace();}
		
	}
	
	public void DeleteUser(int id) {
		String  deleteUser="DELETE FROM  USERS WHERE ID="+id;

		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			stmt.execute(deleteUser);
			System.out.println("User deleted!");
		}catch(SQLException sqle) { sqle.printStackTrace();}
		
	}
	public Users GetUserById(String id) {
		Users user = new Users();
		
		String  selectUser="SELECT FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS WHERE ID="+id;

		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(selectUser);
			
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
			
			System.out.println("User gotted! with id "+id);
		}catch(SQLException sqle) { sqle.printStackTrace();}
		
		
		return user;
	}
	
	public Users GetUserByEmail(String id) {
		Users user = new Users();
		
		String  selectUser="SELECT ID, FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS WHERE EMAIL='"+id+"'";

		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(selectUser);
			
			String Id = res.getString("ID");
			String firstname = res.getString("FIRSTNAME");
			String lastname = res.getString("LASTNAME");
			String email = res.getString("EMAIL");
			String contact = res.getString("CONTACT");
//			Blob image = res.getBlob("IMAGE");
			
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setEmail(email);
			user.setContact(contact);
			user.setId(id);
//			user.setImage(image);
			user.setId(Id);
			System.out.println("User gotted! with id "+id);
			System.out.println("User gotted! with id "+Id);
		}catch(SQLException sqle ) { sqle.printStackTrace();}
		
		
		return user;
	}
	
	public List<Users> GetAllUsers(){
		
		List<Users> userlist = new ArrayList<Users>();

		String  selectUser="SELECT ID, FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS";

		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(selectUser);	

			while(res.next()) {
				Users user = new Users();
				String firstname = res.getString("FIRSTNAME");
				String lastname = res.getString("LASTNAME");
				String email = res.getString("EMAIL");
				String contact = res.getString("CONTACT");
				String id = res.getString("ID");
				Blob image = res.getBlob("IMAGE");
				
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setEmail(email);
				user.setContact(contact);
				user.setId(id);
				user.setImage(image);
				
				userlist.add(user);
				
			System.out.println("User gotted! with id "+id);
			}
		}catch(SQLException sqle) { sqle.printStackTrace();}

		
		return userlist;
	}
	
	public void CreateTable() {
		try(Connection conn = DriverManager.getConnection(dbURL)){			
			Statement stmt = conn.createStatement();
			stmt.execute(createTable);			
		}catch(SQLException sqle) {	sqle.printStackTrace();}
		
	}
}
