package com.piyush004.SportsApi.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ResponseDto {
	private int statusCode;
	private String message;
	private Object data;
}
