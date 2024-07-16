package com.project.server.entity;

import lombok.Data;

@Data
public class LoginResponse {
	private String email;
	private String token;
	private String role;

}
