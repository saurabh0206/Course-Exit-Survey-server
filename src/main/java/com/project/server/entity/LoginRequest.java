package com.project.server.entity;

import lombok.Data;

@Data
public class LoginRequest {
	private String email;
	private String password;

}
