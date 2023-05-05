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

import application.model.UserContents;
import application.model.Users;
import application.util.DateUtil;
import javafx.scene.image.Image;

public class UserContentService {


	  private PreparedStatement store,retrieve;
	public void SaveUser(UserContents userContents) {
		String  insertUser="INSERT INTO USERS_CONTENTS( USERNAME, TITLE, BODY) "
				+ " VALUES (?,?,?)";
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(insertUser);
			store.setString(1, userContents.getUserName());
			store.setString(2, userContents.getTitle());
			store.setString(3, userContents.getBody());
			store.execute();
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateUser(UserContents userContents) {
		String  updateUser="UPDATE USERS_CONTENTS SET TITLE = ? , BODY=? WHERE  USERNAME=?";
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			store = conn.prepareStatement(updateUser);
			store.setString(1, userContents.getTitle());
			store.setString(2, userContents.getBody());
			store.setString(3, userContents.getUserName());
			store.execute();
			System.out.print("Updated here in service!");
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void DeleteUser(int id) {
		String  deleteUser="DELETE FROM  USERS_CONTENTS WHERE ID="+id;

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
	public UserContents GetUserById(String id) {

		UserContents userContents = new UserContents();
		String  selectUser="SELECT  USERNAME, TITLE, BODY FROM USERS_CONTENTS WHERE ID="+id;


		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			retrieve = conn.prepareStatement(selectUser);
			
			 retrieve.setString(1, id);
			  ResultSet res = retrieve.executeQuery();
			 if(res.next()) {
					
					String username = res.getString("USERNAME");
					String title = res.getString("TITLE");
					String body = res.getString("BODY");
					userContents.setUserName(username);
					userContents.setTitle(title);
					userContents.setBody(body);

					System.out.println("User gotted! with id "+id);
					
		}} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userContents;
	}
	
	public List<UserContents> GetUserContentsByEmail(String id) {
		
		List<UserContents> resList = new ArrayList<UserContents>();
		String  selectUser="SELECT ID, TITLE, BODY, TO_CHAR(CREATED_ON,'DD.MM.RRRR') AS CREATED_ON FROM USERS_CONTENTS WHERE USERNAME=?";


		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			retrieve = conn.prepareStatement(selectUser);
			
			 retrieve.setString(1, id);
			  ResultSet res = retrieve.executeQuery();
			  while(res.next()) {
				  UserContents userContents = new UserContents();
				 	int Id = res.getInt("ID");
					String title = res.getString("TITLE");
					String body = res.getString("BODY");
					String createdon = res.getString("CREATED_ON");
					userContents.setUserName(id);
					userContents.setTitle(title);
					userContents.setBody(body);
					userContents.setId(Id);
					userContents.setCreatedOn(DateUtil.parse(createdon));
					
					System.out.println("User gotted! with id "+id);
					System.out.println("User gotted! with id "+Id);
					resList.add(userContents);
					
		}} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return resList;
	}
	
public List<UserContents> GetUserContentsByEmailPagination(String id, String strSearch, int recStart, int recEnd) {
		
		List<UserContents> resList = new ArrayList<UserContents>();
		String  selectUser="SELECT ID, TITLE, BODY, TO_CHAR(CREATED_ON,'DD.MM.RRRR') AS CREATED_ON "
				+ "FROM USERS_CONTENTS WHERE USERNAME=? AND TITLE LIKE ? "
				+ "AND ROWNUM>=? AND ROWNUM<=?";


		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
			retrieve = conn.prepareStatement(selectUser);
			
			 retrieve.setString(1, id);
			 retrieve.setString(2, "%"+strSearch+"%");
			 retrieve.setInt(3, recStart);
			 retrieve.setInt(4, recEnd);
			  ResultSet res = retrieve.executeQuery();
			  while(res.next()) {
				  UserContents userContents = new UserContents();
				 	int Id = res.getInt("ID");
					String title = res.getString("TITLE");
					String body = res.getString("BODY");
					String createdon = res.getString("CREATED_ON");
					userContents.setUserName(id);
					userContents.setTitle(title);
					userContents.setBody(body);
					userContents.setId(Id);
					userContents.setCreatedOn(DateUtil.parse(createdon));
					
					System.out.println("User gotted! with id "+id);
					System.out.println("User gotted! with id "+Id);
					resList.add(userContents);
					
		}} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return resList;
	}


public int GetCountUserContentsByEmail(String id) {
	
	String  selectUser="SELECT COUNT(*) AS TOTAL_RECORDS FROM USERS_CONTENTS WHERE USERNAME=?";

	int TOTALRECORDS=0;
	
	try {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mdap","mdap");
		retrieve = conn.prepareStatement(selectUser);
		
		 retrieve.setString(1, id);
		  ResultSet res = retrieve.executeQuery();
		  while(res.next()) {
			 	TOTALRECORDS = res.getInt("TOTAL_RECORDS");				
	}} catch (SQLException | ClassNotFoundException  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return TOTALRECORDS;
}
	
}
