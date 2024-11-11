package com.piyush004.SportsApi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
			Ground ground = groundRepo.findById(id).filter(g -> !g.isDisable())
					.orElseThrow(() -> new RuntimeException("Ground not found or is disabled"));

			// Set basic fields
			setBasicGroundInfo(ground, groundInfoRes);

			// Map user info
			ground.getUsers().stream().findFirst().ifPresent(user -> {
				ResponseDto.UserInfoRes userInfoRes = new ResponseDto.UserInfoRes();
				userInfoRes.setFirstName(user.getFirstName());
				userInfoRes.setLastName(user.getLastName());
				userInfoRes.setEmail(user.getEmail());
				userInfoRes.setMobileNo(user.getMobileNo());
				userInfoRes.setCity(user.getCity());
				userInfoRes.setDisable(user.isDisable());
				groundInfoRes.setUser(userInfoRes);
			});

			// Map available sports
			groundInfoRes.setAvailableSports(mapSports(ground));

			// Map amenities
			groundInfoRes.setAmenities(mapAmenities(ground));

			// Map available times
			groundInfoRes.setAvailableTimes(mapAvailableTimes(ground));

			// Map images
			groundInfoRes.setImages(mapImages(ground));

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

	public ResOutRes getAllGroundsInfo() {
		ResOutRes reqRes = new ResOutRes();
		List<ResponseDto.GroundInfoRes> groundInfoResList = new ArrayList<>();

		try {
			List<Ground> grounds = groundRepo.findAll().stream().filter(ground -> !ground.isDisable())
					.collect(Collectors.toList());

			grounds.forEach(ground -> {
				// Create a new GroundInfoRes for each ground
				ResponseDto.GroundInfoRes groundInfoRes = new ResponseDto.GroundInfoRes();

				// Set basic fields
				setBasicGroundInfo(ground, groundInfoRes);

				// Map user info (assuming a single user per ground)
				ground.getUsers().stream().findFirst().ifPresent(user -> {
					ResponseDto.UserInfoRes userInfoRes = new ResponseDto.UserInfoRes();
					userInfoRes.setFirstName(user.getFirstName());
					userInfoRes.setLastName(user.getLastName());
					userInfoRes.setEmail(user.getEmail());
					userInfoRes.setMobileNo(user.getMobileNo());
					userInfoRes.setCity(user.getCity());
					userInfoRes.setDisable(user.isDisable());
					groundInfoRes.setUser(userInfoRes);
				});

				// Map available sports
				groundInfoRes.setAvailableSports(mapSports(ground));

				// Map amenities
				groundInfoRes.setAmenities(mapAmenities(ground));

				// Map available times
				groundInfoRes.setAvailableTimes(mapAvailableTimes(ground));

				// Map images
				groundInfoRes.setImages(mapImages(ground));

				groundInfoResList.add(groundInfoRes);
			});

			reqRes.setData(groundInfoResList);
			reqRes.setStatusCode(200);
			reqRes.setMessage("Ground information retrieved successfully");
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred: " + e.getMessage());
		}
		return reqRes;
	}

	// Common method to set basic ground info
	private void setBasicGroundInfo(Ground ground, ResponseDto.GroundInfoRes groundInfoRes) {
		groundInfoRes.setGroundId(ground.getGroundId());
		groundInfoRes.setName(ground.getName());
		groundInfoRes.setDescription(ground.getDescription());
		groundInfoRes.setWidth(ground.getWidth());
		groundInfoRes.setLength(ground.getLength());
		groundInfoRes.setHeight(ground.getHeight());
		groundInfoRes.setLocation(ground.getLocation());
		groundInfoRes.setLocationUrl(ground.getLocationUrl());
	}

	// Common method to map available sports
	private Set<ResponseDto.SportInfoRes> mapSports(Ground ground) {
		return ground.getAvailableSports().stream().map(sport -> {
			ResponseDto.SportInfoRes sportInfoRes = new ResponseDto.SportInfoRes();
			sportInfoRes.setSportName(sport.getSportName());
			sportInfoRes.setDescription(sport.getDescription());
			sportInfoRes.setPrice(sport.getPrice());
			return sportInfoRes;
		}).collect(Collectors.toSet());
	}

	// Common method to map amenities
	private Set<ResponseDto.AmenityInfoRes> mapAmenities(Ground ground) {
		return ground.getAmenities().stream().map(amenity -> {
			ResponseDto.AmenityInfoRes amenityInfoRes = new ResponseDto.AmenityInfoRes();
			amenityInfoRes.setName(amenity.getName());
			amenityInfoRes.setDescription(amenity.getDescription());
			return amenityInfoRes;
		}).collect(Collectors.toSet());
	}

	// Common method to map available times
	private Set<ResponseDto.AvailableTimeInfoRes> mapAvailableTimes(Ground ground) {
		return ground.getAvailableTimes().stream().map(time -> {
			ResponseDto.AvailableTimeInfoRes timeInfoRes = new ResponseDto.AvailableTimeInfoRes();
			timeInfoRes.setName(time.getName());
			timeInfoRes.setStartTiming(time.getStartTiming());
			timeInfoRes.setEndTiming(time.getEndTiming());
			return timeInfoRes;
		}).collect(Collectors.toSet());
	}

	// Common method to map images
	private Set<ResponseDto.GroundImageInfoRes> mapImages(Ground ground) {
		return ground.getImages().stream().map(image -> {
			ResponseDto.GroundImageInfoRes imageInfoRes = new ResponseDto.GroundImageInfoRes();
			imageInfoRes.setIsUrl(image.getIsUrl());
			imageInfoRes.setImageUrl(image.getImageUrl());
			return imageInfoRes;
		}).collect(Collectors.toSet());
	}

}
