package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.AuthResponse;
import com.openclassrooms.mddapi.model.Login;
import com.openclassrooms.mddapi.model.Register;
import com.openclassrooms.mddapi.security.JwtService;
import com.openclassrooms.mddapi.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Register register) {
		try {
			log.info("Start register");
			User registerUser = (User) authService.register(register);
			String token = jwtService.generateToken(registerUser);
			log.info("Token is : {}", token);
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (Exception e) {
			log.error("Error occurred while registering user : {}", e.getMessage());
			log.error("Error stacktrace : ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		try {
			log.info("Start login");
			String token = authService.login(login);
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (Exception e) {
			log.error("Error occurred while fetching current user : {}", e.getMessage());
			log.error("Error stacktrace : ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
