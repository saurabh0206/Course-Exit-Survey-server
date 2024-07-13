package com.project.server.entity;

public class GoogleLoginRequest {
    private GoogleData data;

    // Getters and setters

    public static class GoogleData {
        private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

        // Getters and setters
    }

	public GoogleData getData() {
		return data;
	}

	public void setData(GoogleData data) {
		this.data = data;
	}
}
