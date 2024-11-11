package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.piyush004.SportsApi.dto.RequestDto.GroundRegisterRequest;
import com.piyush004.SportsApi.dto.ResponseDto.ResOutRes;
import com.piyush004.SportsApi.service.GroundService;

import jakarta.validation.Valid;

@RestController
public class GroundController {

	@Autowired
	private GroundService groundService;

	@PostMapping(value = "/admin/ground/ground-register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResOutRes> registerGround(@Valid @RequestBody GroundRegisterRequest request) {
		try {
			ResOutRes response = groundService.registerGround(request);
			return ResponseEntity.status(response.getStatusCode()).body(response);
		} catch (Exception e) {
			// Log the error message (consider using a logger)
			String errorMessage = "Error occurred while registering ground: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(errorMessage));
		}
	}

	@GetMapping(value = "/admin/ground/get-ground/{groundId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResOutRes> getGroundById(@PathVariable Integer groundId) {
		try {
			ResOutRes response = groundService.getGroundById(groundId);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// Log the error message (consider using a logger)
			String errorMessage = "Error occurred while retrieving ground: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(errorMessage));
		}
	}

	@GetMapping(value = "/admin/ground/get-all-ground", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResOutRes> getAllGround() {
		try {
			ResOutRes response = groundService.getAllGroundsInfo();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// Log the error message (consider using a logger)
			String errorMessage = "Error occurred while retrieving all grounds: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(errorMessage));
		}
	}

	// Helper method to create error response
	private ResOutRes createErrorResponse(String message) {
		ResOutRes errorResponse = new ResOutRes();
		errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setMessage(message);
		return errorResponse;
	}
}
