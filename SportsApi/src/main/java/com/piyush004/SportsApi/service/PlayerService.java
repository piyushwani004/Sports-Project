package com.piyush004.SportsApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.piyush004.SportsApi.dto.ResponseDto;
import com.piyush004.SportsApi.entity.Role;
import com.piyush004.SportsApi.entity.User;
import org.springframework.stereotype.Service;
import com.piyush004.SportsApi.repository.PlayerRepo;

@Service
public class PlayerService extends DefaultService {

	@Autowired
	private PlayerRepo playerRepo;

	public ResponseDto getAllPlayers() {
		ResponseDto reqRes = new ResponseDto();
		try {
			List<User> result = playerRepo.getAllPlayers(Role.USER).orElseThrow();
			if (!result.isEmpty()) {
				reqRes.setData(result);
				reqRes.setStatusCode(200);
				reqRes.setMessage("Successful");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("No Player found");
			}
			return reqRes;
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred: " + e.getMessage());
			return reqRes;
		}
	}
}
