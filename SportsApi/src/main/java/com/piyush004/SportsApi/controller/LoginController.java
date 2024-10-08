package com.piyush004.SportsApi.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.piyush004.SportsApi.dto.RequestDto;
import com.piyush004.SportsApi.dto.RequestResponse;
import com.piyush004.SportsApi.service.UsersManagementService;

@RestController
public class LoginController {

    @Autowired
    private UsersManagementService usersManagementService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @PostMapping(value = "/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<RequestResponse> register(@RequestBody RequestResponse request) {
        return ResponseEntity.ok(usersManagementService.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<RequestResponse> login(@RequestBody RequestDto.LoginRequest request) {
        RequestResponse res = new RequestResponse();
        try {
            res = usersManagementService.login(request);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            logger.severe("Error during login: " + e.getMessage());
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request) {
        return ResponseEntity.ok(usersManagementService.refreshToken(request));
    }

}
