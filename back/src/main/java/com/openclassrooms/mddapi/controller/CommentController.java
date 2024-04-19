package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentRequest;
import com.openclassrooms.mddapi.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * Create - Add a new comment to a post.
	 * 
	 * @param commentRequest - An object containing the data of the comment to be
	 *                       saved.
	 * @return The saved comment.
	 */
	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
		try {
			log.info("Create comment : {}", commentRequest);
			this.commentService.createComment(commentRequest);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Exception is : {}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
