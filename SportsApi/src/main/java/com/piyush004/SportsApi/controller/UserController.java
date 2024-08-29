package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.entity.Users;
import com.piyush004.SportsApi.service.UsersManagementService;

@RestController
public class UserController {

	@Autowired
	private UsersManagementService usersManagementService;

	@GetMapping("/user/get-user/{userId}")
	public ResponseEntity<RequestResponse> getUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(usersManagementService.getUsersById(userId));
	}

	@GetMapping("/user/update/{userId}")
	public ResponseEntity<RequestResponse> updateUsers(@PathVariable Integer userId, @RequestBody Users request) {
		return ResponseEntity.ok(usersManagementService.updateUser(userId, request));
	}
	
	@GetMapping("/user/getprofile")
	public ResponseEntity<RequestResponse> getMyProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		RequestResponse response = usersManagementService.getMyInfo(email);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

}
