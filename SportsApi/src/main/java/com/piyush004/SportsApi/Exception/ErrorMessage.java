package com.piyush004.SportsApi.Exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;
}
