package com.project.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {

	@Id
	private String id;
	private String email;
	private String password;
	private String role;
	private List<String> surveys;

	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// Implement the methods

	public String getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String getPassword() {
		return password;
	}
}
