package com.piyush004.SportsApi.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern.Flag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GroundRegisterRequest {

		@NotEmpty(message = "The Ground name is required.")
		@JsonProperty("name")
		private String name;
		private String description;
		private Double width;
		private Double length;
		private Double height;
//		private Double price;
		private String location;
		private String locationUrl;

//		@JsonProperty("availableSports")
//		private Set<Sport> availableSports;
//
//		@JsonProperty("amenities")
//		private Set<Amenity> amenities;
//
//		@JsonProperty("availableTimes")
//		private Set<AvailableTime> availableTimes;
//
//		@JsonProperty("images")
//		private Set<GroundImage> images;
	}

}
