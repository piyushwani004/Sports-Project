package com.piyush004.SportsApi.service;

public class DefaultService {

	public void catchError(Exception e) {
		System.err.println("Error occurred: " + e.getMessage());
		e.printStackTrace();
	}

}
