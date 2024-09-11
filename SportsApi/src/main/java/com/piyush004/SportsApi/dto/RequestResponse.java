package com.piyush004.SportsApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.piyush004.SportsApi.entity.Role;
import com.piyush004.SportsApi.entity.User;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse {

	private int statusCode;
	private String error;
	private String message;
	private String token;
	private String refreshToken;
	private String expirationTime;
	private String firstName;
	private String lastName;
	private String city;
	private Role role;
	private String email;
	private String password;
	private User ourUsers;
	private List<User> ourUsersList;
	private Object data;
}
