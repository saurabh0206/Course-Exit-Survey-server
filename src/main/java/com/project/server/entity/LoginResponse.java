package com.project.server.entity;

import lombok.Data;

@Data
public class LoginResponse {
	public LoginResponse(String email2, String token2, String string) {
        //TODO Auto-generated constructor stub
    }
    private String email;
	private String token;
	private String role;

}
