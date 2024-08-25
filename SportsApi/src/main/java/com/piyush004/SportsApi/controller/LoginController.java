package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.service.UsersManagementService;

@RestController
public class LoginController {

	@Autowired
	private UsersManagementService usersManagementService;

	@PostMapping("/auth/register")
	public ResponseEntity<RequestResponse> register(@RequestBody RequestResponse request) {
		return ResponseEntity.ok(usersManagementService.register(request));
	}

	@PostMapping("/auth/login")
	public ResponseEntity<RequestResponse> login(@RequestBody RequestResponse request) {
		return ResponseEntity.ok(usersManagementService.login(request));
	}
	
	@PostMapping("/auth/refresh-token")
	public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request) {
		return ResponseEntity.ok(usersManagementService.refreshToken(request));
	}

}
