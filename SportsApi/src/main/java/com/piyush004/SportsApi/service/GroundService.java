package com.piyush004.SportsApi.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.piyush004.SportsApi.dto.RequestDto.GroundRegisterRequest;
import com.piyush004.SportsApi.dto.ResponseDto;
import com.piyush004.SportsApi.dto.ResponseDto.ResOutRes;
import com.piyush004.SportsApi.entity.*;
import com.piyush004.SportsApi.repository.AmenityRepo;
import com.piyush004.SportsApi.repository.GroundRepo;
import com.piyush004.SportsApi.repository.SportRepo;
import com.piyush004.SportsApi.repository.UserRepo;

@Service
public class GroundService extends DefaultService {

	@Autowired
	private GroundRepo groundRepo;

	@Autowired
	private SportRepo sportRepo;

	@Autowired
	private AmenityRepo amenityRepo;

	@Autowired
	private UserRepo userRepo;

//    @Transactional
	public ResOutRes registerGround(GroundRegisterRequest request) {
		ResOutRes resp = new ResOutRes();
		try {

			// Validate input data
			if (request.getName() == null || request.getName().isEmpty()) {
				throw new IllegalArgumentException("Ground Name cannot be empty");
			}
			if (request.getLocationUrl() == null || request.getLocationUrl().isEmpty()) {
				throw new IllegalArgumentException("Location URL cannot be empty");
			}
			if (request.getLocation() == null || request.getLocation().isEmpty()) {
				throw new IllegalArgumentException("Location cannot be empty");
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
			ground.setAvailableSports(request.getAvailableSports());
			ground.setAmenities(request.getAmenities());
			ground.setAvailableTimes(request.getAvailableTimes());
			ground.setImages(request.getImages());

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

	public ResOutRes getGroundById(Integer id) {
		ResOutRes reqRes = new ResOutRes();
		ResponseDto.GroundInfoRes groundInfoRes = new ResponseDto.GroundInfoRes();
		try {
			Ground ground = groundRepo.findById(id).orElseThrow(() -> new RuntimeException("Ground Not found"));
			// Set basic fields
		    groundInfoRes.setGroundId(ground.getGroundId());
		    groundInfoRes.setName(ground.getName());
		    groundInfoRes.setDescription(ground.getDescription());
		    groundInfoRes.setWidth(ground.getWidth());
		    groundInfoRes.setLength(ground.getLength());
		    groundInfoRes.setHeight(ground.getHeight());
		    groundInfoRes.setLocation(ground.getLocation());
		    groundInfoRes.setLocationUrl(ground.getLocationUrl());
		    
		    // Map user info
		    ResponseDto.UserInfoRes userInfoRes = new ResponseDto.UserInfoRes();
		    if (!ground.getUsers().isEmpty()) {
		        User user = ground.getUsers().iterator().next();  // Assuming a single user per ground
		        userInfoRes.setFirstName(user.getFirstName());
		        userInfoRes.setLastName(user.getLastName());
		        userInfoRes.setEmail(user.getEmail());
		        userInfoRes.setMobileNo(user.getMobileNo());
		        userInfoRes.setCity(user.getCity());
		        userInfoRes.setDisable(user.isDisable());
		        groundInfoRes.setUser(userInfoRes);
		    }

		    // Map available sports
		    Set<ResponseDto.SportInfoRes> sports = new HashSet<>();
		    ground.getAvailableSports().forEach(sport -> {
		    	ResponseDto.SportInfoRes sportInfoRes = new ResponseDto.SportInfoRes();
		        sportInfoRes.setSportName(sport.getSportName());
		        sportInfoRes.setDescription(sport.getDescription());
		        sportInfoRes.setPrice(sport.getPrice());
		        sports.add(sportInfoRes);
		    });
		    groundInfoRes.setAvailableSports(sports);

		    // Map amenities
		    Set<ResponseDto.AmenityInfoRes> amenities = new HashSet<>();
		    ground.getAmenities().forEach(amenity -> {
		    	ResponseDto.AmenityInfoRes amenityInfoRes = new ResponseDto.AmenityInfoRes();
		        amenityInfoRes.setName(amenity.getName());
		        amenityInfoRes.setDescription(amenity.getDescription());
		        amenities.add(amenityInfoRes);
		    });
		    groundInfoRes.setAmenities(amenities);

		    // Map available times
		    Set<ResponseDto.AvailableTimeInfoRes> availableTimes = new HashSet<>();
		    ground.getAvailableTimes().forEach(time -> {
		    	ResponseDto.AvailableTimeInfoRes timeInfoRes = new ResponseDto.AvailableTimeInfoRes();
		        timeInfoRes.setName(time.getName());
		        timeInfoRes.setStartTiming(time.getStartTiming());
		        timeInfoRes.setEndTiming(time.getEndTiming());
		        availableTimes.add(timeInfoRes);
		    });
		    groundInfoRes.setAvailableTimes(availableTimes);

		    // Map images
		    Set<ResponseDto.GroundImageInfoRes> images = new HashSet<>();
		    ground.getImages().forEach(image -> {
		    	ResponseDto.GroundImageInfoRes imageInfoRes = new ResponseDto.GroundImageInfoRes();
		        imageInfoRes.setIsUrl(image.getIsUrl());
		        imageInfoRes.setImageUrl(image.getImageUrl());
		        images.add(imageInfoRes);
		    });
		    groundInfoRes.setImages(images);
			
			reqRes.setData(groundInfoRes);
			reqRes.setStatusCode(200);
			reqRes.setMessage("Ground with name '" + groundInfoRes.getName() + "' found successfully");
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred: " + e.getMessage());
		}
		return reqRes;
	}

}
