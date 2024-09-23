package com.piyush004.SportsApi.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern.Flag;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.piyush004.SportsApi.entity.Amenity;
import com.piyush004.SportsApi.entity.AvailableTime;
import com.piyush004.SportsApi.entity.GroundImage;
import com.piyush004.SportsApi.entity.Sport;

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
		private Double price;
		private String location;
		private String locationUrl;

		@NotEmpty(message = "Sports cannot be empty.")
	    @JsonProperty("availableSports")
	    private Set<Integer> availableSports;  // Changed to Set of Sport IDs

	    @NotEmpty(message = "Amenities cannot be empty.")
	    @JsonProperty("amenities")
	    private Set<Integer> amenities;  // Changed to Set of Amenity IDs

	    @NotEmpty(message = "Available times cannot be empty.")
	    @JsonProperty("availableTimes")
	    private Set<AvailableTime> availableTimes;  

	    @JsonProperty("images")
	    private Set<GroundImage> images;  // Using a custom DTO for images
	}

}
