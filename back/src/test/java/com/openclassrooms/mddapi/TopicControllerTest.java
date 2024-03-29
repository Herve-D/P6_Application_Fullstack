package com.openclassrooms.mddapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.mddapi.controller.TopicController;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.TopicService;

@SpringBootTest
public class TopicControllerTest {

	@Mock
	TopicService topicService;

	@InjectMocks
	TopicController topicController;

	@Test
	public void getAll() {
		List<TopicDto> topicDtos = new ArrayList<>();
		TopicDto topicDto = new TopicDto();
		topicDto.setName("test");
		topicDtos.add(topicDto);

		when(topicService.getTopics()).thenReturn(topicDtos);

		ResponseEntity<?> response = topicController.getTopics();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(response.getBody(), topicDtos);
	}

}
