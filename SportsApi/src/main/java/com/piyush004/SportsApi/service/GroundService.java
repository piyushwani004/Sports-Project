package com.piyush004.SportsApi.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.piyush004.SportsApi.dto.RequestDto.GroundRegisterRequest;
import com.piyush004.SportsApi.dto.RequestResponse;
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

	@Transactional(readOnly = true, rollbackFor = Exception.class )
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

			// Get the current authenticated user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = authentication.getName();
			User userOptional = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not found"));

			// Initialize the Ground entity
	        Ground ground = new Ground();
	        ground.setName(request.getName());
	        ground.setDescription(request.getDescription());
	        ground.setWidth(request.getWidth());
	        ground.setLength(request.getLength());
	        ground.setHeight(request.getHeight());
	        ground.setLocation(request.getLocation());
	        ground.setLocationUrl(request.getLocationUrl());
	        ground.setUser(userOptional);

	        // Set Sports
	        if (request.getAvailableSports() != null && !request.getAvailableSports().isEmpty()) {
	            Set<Sport> sports = new HashSet<>(sportRepo.findAllById(request.getAvailableSports()));
	            if (sports.size() != request.getAvailableSports().size()) {
	                throw new IllegalArgumentException("Invalid sports provided.");
	            }
	            ground.setSports(sports);
	        } else {
	            throw new IllegalArgumentException("Sports cannot be empty");
	        }
	        
	        // Set Amenities
	        if (request.getAmenities() != null && !request.getAmenities().isEmpty()) {
	            Set<Amenity> amenities = new HashSet<>(amenityRepo.findAllById(request.getAmenities()));
	            if (amenities.size() != request.getAmenities().size()) {
	                throw new IllegalArgumentException("Invalid amenities provided.");
	            }
	            ground.setAmenities(amenities);
	        }
	        
	        // Set Available Times
	        ground.setAvailableTimes(request.getAvailableTimes());
	        
	        // Set Images
	        if (request.getImages() != null && !request.getImages().isEmpty()) {
	            Set<GroundImage> images = new HashSet<>();
	            for (GroundImage imgRequest : request.getImages()) {
	                GroundImage image = new GroundImage();
	                image.setIsUrl(imgRequest.getIsUrl());
	                image.setImageUrl(imgRequest.getImageUrl());
	                image.setImage(imgRequest.getImage());
	                image.setGround(ground);
	                images.add(image);
	            }
	            ground.setImages(images);
	        }
	        
	        
			// Save the ground to the database
	        Ground savedGround = groundRepo.save(ground);

	        // Prepare a successful response
	        resp.setData(savedGround);
	        resp.setMessage("Ground registered successfully");
	        resp.setStatusCode(200);


			
		} catch (IllegalArgumentException e) {
			resp.setStatusCode(400); // Bad Request
			resp.setError(e.getMessage());
			throw e;
		} catch (Exception e) {
			catchError(e);
			resp.setStatusCode(500); // Internal Server Error
			resp.setError("An unexpected error occurred: " + e.getMessage());
			throw e;
		}
		return resp;
	}
	
	
	public RequestResponse getGroundById(Integer id) {
		RequestResponse reqRes = new RequestResponse();
		try {
			Ground  groundById = groundRepo.findByIdWithDetails(id).orElseThrow(() -> new RuntimeException("Ground Not found"));
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
