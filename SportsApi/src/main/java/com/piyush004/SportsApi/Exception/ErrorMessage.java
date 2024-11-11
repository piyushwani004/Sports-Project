package com.piyush004.SportsApi.Exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;
	
	// Additional fields for exception details
	private String className;
	private String methodName;
	private String fileName;
	private int lineNumber;
}
