package com.project.server.entity;

import lombok.Data;

@Data
public class GoogleLoginRequest {
	private GoogleData data;

	// Getters and setters
	@Data
	public static class GoogleData {
		private String email;
	}

}
