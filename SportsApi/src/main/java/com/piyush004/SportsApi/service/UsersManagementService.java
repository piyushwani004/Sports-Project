package com.piyush004.SportsApi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piyush004.SportsApi.dto.RequestDto;
import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.entity.Role;
import com.piyush004.SportsApi.entity.User;
import com.piyush004.SportsApi.repository.UserRepo;

@Service
public class UsersManagementService extends DefaultService {

	@Autowired
	private UserRepo usersRepo;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public RequestResponse register(RequestResponse request) {
		RequestResponse resp = new RequestResponse();
		try {

			User users = new User();
			users.setEmail(request.getEmail());
			users.setCity(request.getCity());
			users.setRole(request.getRole());
			users.setFirstName(request.getFirstName());
			users.setLastName(request.getLastName());
			users.setPassword(passwordEncoder.encode(request.getPassword()));
			User usersResult = usersRepo.save(users);
			if (usersResult.getId() > 0) {
				resp.setOurUsers((usersResult));
				resp.setMessage("User Saved Successfully");
				resp.setStatusCode(200);
			}

		} catch (Exception e) {
			catchError(e);
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}

	public RequestResponse login(RequestDto.LoginRequest loginRequest) {
		RequestResponse resp = new RequestResponse();
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
			var jwt = jwtUtils.generateToken(user);
			var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
			resp.setStatusCode(200);
			resp.setToken(jwt);
			resp.setRole(user.getRole());
			resp.setRefreshToken(refreshToken);
			resp.setExpirationTime("24Hrs");
			resp.setMessage("Successfully Logged In");

		} catch (Exception e) {
			catchError(e);
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}

	public RequestResponse refreshToken(RequestResponse refreshTokenReqiest) {
		RequestResponse response = new RequestResponse();
		try {
			String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
			User users = usersRepo.findByEmail(ourEmail).orElseThrow();
			if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
				var jwt = jwtUtils.generateToken(users);
				response.setStatusCode(200);
				response.setToken(jwt);
				response.setRefreshToken(refreshTokenReqiest.getToken());
				response.setExpirationTime("24Hr");
				response.setMessage("Successfully Refreshed Token");
			}
			response.setStatusCode(200);
			return response;

		} catch (Exception e) {
			catchError(e);
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
			return response;
		}
	}

	public RequestResponse getAllUsers() {
		RequestResponse reqRes = new RequestResponse();
		try {
			List<User> result = usersRepo.findAll();
			if (!result.isEmpty()) {
				reqRes.setOurUsersList(result);
				reqRes.setStatusCode(200);
				reqRes.setMessage("Successful");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("No users found");
			}
			return reqRes;
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred: " + e.getMessage());
			return reqRes;
		}
	}

	public RequestResponse getUsersById(Integer id) {
		RequestResponse reqRes = new RequestResponse();
		try {
			User usersById = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
			reqRes.setOurUsers(usersById);
			reqRes.setStatusCode(200);
			reqRes.setMessage("Users with id '" + id + "' found successfully");
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred: " + e.getMessage());
		}
		return reqRes;
	}

	public RequestResponse deleteUser(Integer userId) {
		RequestResponse reqRes = new RequestResponse();
		try {
			Optional<User> userOptional = usersRepo.findById(userId);
			if (userOptional.isPresent()) {
				usersRepo.deleteById(userId);
				reqRes.setStatusCode(200);
				reqRes.setMessage("User deleted successfully");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("User not found for deletion");
			}
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
		}
		return reqRes;
	}

	public RequestResponse updateUser(Integer userId, User updatedUser) {
		RequestResponse reqRes = new RequestResponse();
		try {
			Optional<User> userOptional = usersRepo.findById(userId);
			if (userOptional.isPresent()) {
				User existingUser = userOptional.get();
				existingUser.setEmail(updatedUser.getEmail());
				existingUser.setFirstName(updatedUser.getFirstName());
				existingUser.setLastName(updatedUser.getLastName());
				existingUser.setCity(updatedUser.getCity());
				existingUser.setRole(updatedUser.getRole());

				// Check if password is present in the request
				if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
					// Encode the password and update it
					existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
				}

				User savedUser = usersRepo.save(existingUser);
				reqRes.setOurUsers(savedUser);
				reqRes.setStatusCode(200);
				reqRes.setMessage("User updated successfully");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("User not found for update");
			}
		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
		}
		return reqRes;
	}

	public RequestResponse getMyInfo(String email) {
		RequestResponse reqRes = new RequestResponse();
		try {
			Optional<User> userOptional = usersRepo.findByEmail(email);
			if (userOptional.isPresent()) {
				reqRes.setOurUsers(userOptional.get());
				reqRes.setStatusCode(200);
				reqRes.setMessage("successful");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("User not found for update");
			}

		} catch (Exception e) {
			catchError(e);
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
		}
		return reqRes;

	}

}
