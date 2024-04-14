package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRequest;
import com.openclassrooms.mddapi.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
		try {
			log.info("Create post : ", postRequest);
			this.postService.createPost(postRequest);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<PostDto>> getPosts() {
		log.info("Retrieve all posts");
		try {
			return ResponseEntity.ok(postService.getPosts());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
