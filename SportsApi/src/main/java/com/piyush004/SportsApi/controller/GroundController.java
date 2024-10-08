package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
		ResOutRes response = groundService.registerGround(request);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@GetMapping(value = "/admin/ground/get-ground/{groundId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResOutRes> getGroundById(@PathVariable Integer groundId) {
		return ResponseEntity.ok(groundService.getGroundById(groundId));
	}
}
