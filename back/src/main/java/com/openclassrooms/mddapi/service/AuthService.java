package com.openclassrooms.mddapi.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Login;
import com.openclassrooms.mddapi.model.MddUser;
import com.openclassrooms.mddapi.model.Register;
import com.openclassrooms.mddapi.repository.IUserRepository;
import com.openclassrooms.mddapi.security.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public UserDetails register(Register register) {
		if (existsByEmail(register.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		MddUser user = new MddUser(register.getEmail(), register.getName(),
				passwordEncoder.encode(register.getPassword()));
		userRepository.save(user);

		return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
	}

	public String login(Login login) throws BadCredentialsException {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
			User authenticatedUser = (User) authenticate.getPrincipal();
			String token = jwtService.generateToken(authenticatedUser);
			log.info("Token is : {}", token);
			return token;
		} catch (AuthenticationException e) {
			log.error(e.getMessage());
			throw new BadCredentialsException("Invalid credentials");
		}
	}

}
