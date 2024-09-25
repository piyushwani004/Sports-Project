package com.piyush004.SportsApi.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ResponseDto {

	@Data
	public static class ResOutRes {
		private int statusCode;
		private String error;
		private String message;
		private Object data;
	}

	@Data
	public static class UserInfoRes {
		private String firstName;
		private String lastName;
		private String email;
		private String mobileNo;
		private String city;
		private boolean isDisable;
	}

	@Data
	public static class SportInfoRes {
		private String sportName;
		private String description;
		private BigDecimal price;
	}

	@Data
	public static class AmenityInfoRes {
		private String name;
		private String description;
	}

	@Data
	public static class AvailableTimeInfoRes {
		private String name;
		private String startTiming;
		private String endTiming;
	}
	
	@Data
	public static class GroundImageInfoRes {
		private Boolean isUrl = false;
		private String imageUrl;
	}

	@Data
	public static class GroundInfoRes {

		private Integer groundId;
		private String name;
		private String description;
		private Double width;
		private Double length;
		private Double height;
		private String location;
		private String locationUrl;
		private UserInfoRes user;
		private Set<SportInfoRes> availableSports = new HashSet<>();
		private Set<AmenityInfoRes> amenities = new HashSet<>();
		private Set<AvailableTimeInfoRes> availableTimes = new HashSet<>();
		private Set<GroundImageInfoRes> images = new HashSet<>();
	}

}
