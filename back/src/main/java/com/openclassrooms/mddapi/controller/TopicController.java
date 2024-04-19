package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.TopicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	/**
	 * Get all topics.
	 * 
	 * @return A list object of all topics.
	 */
	@GetMapping
	public ResponseEntity<List<TopicDto>> getTopics() {
		log.info("Retrieve all topics");
		try {
			return ResponseEntity.ok(topicService.getTopics());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Subscribe a topic to an user.
	 * 
	 * @param id     - The id of the topic.
	 * @param userId - The id of the user.
	 * @return
	 */
	@PostMapping("/{id}/subscribe/{userId}")
	public ResponseEntity<?> subscribe(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		try {
			this.topicService.subscribe(Long.parseLong(id), Long.parseLong(userId));
			log.info("Subscription saved !");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Unsubscribe a topic from a user.
	 * 
	 * @param id     - The id of the topic.
	 * @param userId - The id of the user.
	 * @return
	 */
	@DeleteMapping("/{id}/subscribe/{userId}")
	public ResponseEntity<?> unSubscribe(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		try {
			this.topicService.unSubscribe(Long.parseLong(id), Long.parseLong(userId));
			log.info("Subscription deleted !");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
