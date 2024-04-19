package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.MddUserDto;
import com.openclassrooms.mddapi.model.MddUser;
import com.openclassrooms.mddapi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/me")
	public ResponseEntity<MddUserDto> getCurrentUser() {
		try {
			MddUserDto userDto = this.userService.getCurrentUser();
			if (userDto != null) {
				return ResponseEntity.ok(userDto);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (UsernameNotFoundException e) {
			log.error("User not found : {}", e.getMessage(), e);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error occurred while fetching current user : {}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
		try {
			log.info("Fetch user : {}", id);
			MddUserDto userDto = this.userService.getUserById(Long.parseLong(id));
			if (userDto == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(userDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody MddUserDto user) {
		try {
			log.info("Updating user : {}", id);
			MddUser updatedUser = this.userService.updateUser(Long.parseLong(id), user);
			log.info("User updated : {}", updatedUser);
			return ResponseEntity.ok().body(this.userService.toDto(updatedUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}/topics")
	public ResponseEntity<?> getSubscriptions(@PathVariable("id") String id) {
		try {
			MddUserDto user = this.userService.getUserById(Long.parseLong(id));
			return ResponseEntity.ok(user.getTopics());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
