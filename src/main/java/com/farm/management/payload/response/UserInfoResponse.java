package com.farm.management.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String firstname;
	private String lastname;

	public UserInfoResponse(Long id, String username, String email, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
