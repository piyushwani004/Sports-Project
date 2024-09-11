package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.piyush004.SportsApi.dto.RequestDto.GroundRegisterRequest;
import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.service.GroundService;

import jakarta.validation.Valid;

@RestController
public class GroundController {

	@Autowired
	private GroundService groundService;

	@PostMapping(value = "/admin/ground/ground-register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequestResponse> registerGround(@Valid @RequestBody GroundRegisterRequest request) {
		RequestResponse response = groundService.registerGround(request);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
}
