package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.ITopicRepository;

@Service
public class TopicService {

	@Autowired
	private ITopicRepository topicRepository;

	@Autowired
	private ModelMapper modelMapper;

	private TopicDto toDto(Topic topic) {
		return modelMapper.map(topic, TopicDto.class);
	}

	private Topic toEntity(TopicDto topicDto) {
		return modelMapper.map(topicDto, Topic.class);
	}
	
	public TopicDto getTopicById(Long id) {
		return toDto(this.topicRepository.findById(id).orElse(null));
	}

	public List<TopicDto> getTopics() {
		return topicRepository.findAll().stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

}
