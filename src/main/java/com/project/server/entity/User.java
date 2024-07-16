package com.project.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.List;

@Document(collection = "users")
@Data
public class User {

	public User(String teacherEmail, String hashpw, String string) {
		//TODO Auto-generated constructor stub
	}
	@Id
	private String id;
	private String email;
	private String password;
	private String role;
	private List<String> surveys;

}
