package com.project.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {

	@Id
	private String id;
	private String email;
	private String password;
	private String role;
	private List<String> surveys;

	// Getters and setters

	public User(String teacherEmail, String hashpw, String string) {
        //TODO Auto-generated constructor stub
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<String> surveys) {
		this.surveys = surveys;
	}
}
