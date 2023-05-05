package application.model;

import java.time.LocalDate;

public class UserContents {

	private int Id;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	private String UserName;
	private String Title;
	private String Body;
	private LocalDate CreatedOn;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public LocalDate getCreatedOn() {
		return CreatedOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		CreatedOn = createdOn;
	}
	
	
}
