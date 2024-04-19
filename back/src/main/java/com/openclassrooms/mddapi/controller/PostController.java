package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRequest;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	/**
	 * Create a new post.
	 * 
	 * @param postRequest - An object containing the data of the post to be saved.
	 * @return The saved post.
	 */
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
		try {
			log.info("Create post request : {}", postRequest);
			Post post = this.postService.createPost(postRequest);
			log.info("Post saved : {}", post);
			return ResponseEntity.ok().body(this.postService.toDto(post));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get a post using an id.
	 * 
	 * @param id - The id of the post.
	 * @return A filled post object.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById(@PathVariable("id") String id) {
		try {
			log.info("Retrieving post : {}", id);
			PostDto postDto = this.postService.getPostById(Long.parseLong(id));
			if (postDto == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(postDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getPosts() {
		try {
			log.info("Retrieve all posts");
			return ResponseEntity.ok().body(this.postService.getPosts());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
