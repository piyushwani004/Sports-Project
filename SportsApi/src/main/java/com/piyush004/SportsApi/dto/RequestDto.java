package com.piyush004.SportsApi.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern.Flag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class RequestDto {

	@Data
	public static class LoginRequest {
		@NotEmpty(message = "The email address is required.")
		@Email(message = "The email address is invalid.", flags = Flag.CASE_INSENSITIVE)
		private String email;

		@NotEmpty(message = "Password is required")
		private String password;
	}
}
