package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.service.UsersManagementService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class AdminAccessController {

	@Autowired
	private UsersManagementService usersManagementService;

	@GetMapping("/admin/get-all-users")
	public ResponseEntity<RequestResponse> getAllUsers() {
		return ResponseEntity.ok(usersManagementService.getAllUsers());
	}

	@DeleteMapping("/admin/delete/{userId}")
	public ResponseEntity<RequestResponse> deleteUSer(@PathVariable Integer userId) {
		return ResponseEntity.ok(usersManagementService.deleteUser(userId));
	}

	@GetMapping("/adminuser/get-profile")
	public ResponseEntity<RequestResponse> getMyProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		RequestResponse response = usersManagementService.getMyInfo(email);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
}
