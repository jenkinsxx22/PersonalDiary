package application;

import java.util.ArrayList;
import java.util.List;

import application.model.UserContents;
import application.model.Users;
import application.service.UserContentService;
import application.service.UserService;

public class MainAppTest {

	public static void main (String args[]) {
		
//		Users user = new Users();
//		user.setEmail("admin@example.com");
//		UserService userservice = new UserService();
////		userservice.SaveUser(user);
//	
//		
//		user = userservice.GetUserByEmail("admin@example.com");
//		user.setFirstName("abc");
//		user.setLastName("def");
//		userservice.UpdateUser(user);
//		System.out.println(user.getId()+"\n"+user.getFirstName()+"\n"+user.getLastName()+"\n"+user.getContact()+"\n"+user.getEmail());

		UserContents userContents = new UserContents();
		List<UserContents> resList = new ArrayList<UserContents>();
		UserContentService contentservice = new UserContentService();
		resList = contentservice.GetUserContentsByEmail("nisar");
		
		resList.forEach(u->{
			System.out.println("Title: "+u.getTitle() +" Body:"+u.getBody());
		});
		System.out.println(Math.floorMod(8, 6));
	}
}
