package com.piyush004.SportsApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piyush004.SportsApi.dto.ResponseDto;
import com.piyush004.SportsApi.service.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping("/admin/get-all-players")
	public ResponseEntity<ResponseDto> getAllPlayers() {
		return ResponseEntity.ok(playerService.getAllPlayers());
	}
}
