package com.piyush004.SportsApi.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.piyush004.SportsApi.dto.RequestDto.GroundRegisterRequest;
import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.entity.*;
import com.piyush004.SportsApi.repository.GroundRepo;
import com.piyush004.SportsApi.repository.UserRepo;

@Service
public class GroundService extends DefaultService {

	@Autowired
	private GroundRepo groundRepo;

//	@Autowired
//	private SportRepo sportRepo;
//
//	@Autowired
//	private AmenityRepo amenityRepo;

	@Autowired
	private UserRepo userRepo;

//    @Transactional
	public RequestResponse registerGround(GroundRegisterRequest request) {
		RequestResponse resp = new RequestResponse();
		try {

			// Validate input data
			if (request.getName() == null || request.getName().isEmpty()) {
				throw new IllegalArgumentException("Ground Name cannot be empty");
			}
			if (request.getLocationUrl() == null || request.getLocationUrl().isEmpty()) {
				throw new IllegalArgumentException("Location URL cannot be empty");
			}

			// Check for existing ground by name and location
			Optional<Ground> existingGround = groundRepo.findByNameAndLocationUrl(request.getName(),
					request.getLocationUrl());
			if (existingGround.isPresent()) {
				throw new IllegalArgumentException("Ground details with this name and location already exist");
			}

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = authentication.getName();
			Optional<User> userOptional = userRepo.findByEmail(email);
			Set<User> tempusers = new HashSet<>();
			if (userOptional.isPresent()) {
				tempusers.add(userOptional.get());
			}

			Ground ground = new Ground();
			ground.setName(request.getName());
			ground.setDescription(request.getDescription());
			ground.setWidth(request.getWidth());
			ground.setLength(request.getLength());
			ground.setHeight(request.getHeight());
			ground.setLocation(request.getLocation());
			ground.setLocationUrl(request.getLocationUrl());
			ground.setUsers(tempusers);
//			ground.setAvailableSports(request.getAvailableSports());
//			ground.setAmenities(request.getAmenities());
//			ground.setAvailableTimes(request.getAvailableTimes());
//			ground.setImages(request.getImages());

			// Save the ground to the database
			Ground savedGround = groundRepo.save(ground);

			// Prepare a successful response
			resp.setData(savedGround);
			resp.setMessage("Ground registered successfully");
			resp.setStatusCode(200);

		} catch (IllegalArgumentException e) {
			resp.setStatusCode(400); // Bad Request
			resp.setError(e.getMessage());
		} catch (Exception e) {
			catchError(e);
			resp.setStatusCode(500); // Internal Server Error
			resp.setError("An unexpected error occurred: " + e.getMessage());
		}
		return resp;
	}

	public RequestResponse getGroundById(Integer id) {
		RequestResponse reqRes = new RequestResponse();
		try {
			Ground groundById = groundRepo.findById(id).orElseThrow(() -> new RuntimeException("Ground Not found"));

			System.out.println("\n Ground: " + groundById);
			System.out.println("\n User names: " + groundById.getUsers());
//			System.out.println("\n Times: " + groundById.getAvailableTimes());
//			System.out.println("\n Images: " + groundById.getImages());
//			System.out.println("\n Sports: " + groundById.getAvailableSports());
//			System.out.println("\n Amenities: " + groundById.getAmenities());

			reqRes.setData(groundById);
			reqRes.setStatusCode(200);
			reqRes.setMessage("Ground with name '" + groundById.getName() + "' found successfully");
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred: " + e.getMessage());
		}
		return reqRes;
	}

}
