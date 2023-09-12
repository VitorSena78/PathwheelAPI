package com.pathwheel.model;

//import org.codehaus.jackson.map.annotate.JsonSerialize;//esse import permite que você faça o uso da biblioteca Jackson para serializar objetos Java em formato JSON de forma personalizada.

import com.fasterxml.jackson.databind.annotation.JsonSerialize;//Essa biblioteca oferece recursos mais avançados para a serialização e desserialização de objetos Java em JSON.

import java.sql.ResultSet;
import java.sql.SQLException;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {
	private Long id;
	private String fullName;
	private String userName;
	private String email;
	private String registrationDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", user_name=" + userName + ", email=" + email
				+ ", registrationDate=" + registrationDate + "]";
	}
	
	public static User parse(ResultSet rs) throws SQLException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setUserName(rs.getString("user_name"));
		user.setFullName(rs.getString("full_name"));
		user.setId(rs.getLong("id"));		
		return user;
	}	
	
}
