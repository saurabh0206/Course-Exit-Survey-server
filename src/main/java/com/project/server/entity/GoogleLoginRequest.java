package com.project.server.entity;

import lombok.Data;

@Data
public class GoogleLoginRequest {
	private GoogleData data;

	@Data
	public static class GoogleData {
		private String email;
	}

	public String getEmail() {
		return data != null ? data.getEmail() : null;
	}
}
