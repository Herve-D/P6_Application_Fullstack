package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.MddUser;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.ITopicRepository;
import com.openclassrooms.mddapi.repository.IUserRepository;

@Service
public class TopicService {

	@Autowired
	private ITopicRepository topicRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	private TopicDto toDto(Topic topic) {
		return this.modelMapper.map(topic, TopicDto.class);
	}

	public TopicDto getTopicById(Long id) {
		return this.toDto(this.topicRepository.findById(id).orElse(null));
	}

	public List<TopicDto> getTopics() {
		return this.topicRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public void subscribe(Long id, Long userId) {
		Topic topic = this.topicRepository.findById(id).orElse(null);
		MddUser user = this.userRepository.findById(userId).orElse(null);
		if (topic == null || user == null) {
			throw new NotFoundException();
		}
		boolean alreadySubscribed = user.getTopics().stream().anyMatch(o -> o.getId().equals(id));
		if (alreadySubscribed) {
			throw new BadRequestException();
		}
		user.getTopics().add(topic);
		this.userRepository.save(user);
	}

	public void unSubscribe(Long id, Long userId) {
		MddUser user = this.userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new NotFoundException();
		}
		boolean alreadySubscribed = user.getTopics().stream().anyMatch(o -> o.getId().equals(id));
		if (!alreadySubscribed) {
			throw new BadRequestException();
		}
		user.setTopics(
				user.getTopics().stream().filter(topic -> !topic.getId().equals(id)).collect(Collectors.toList()));
		this.userRepository.save(user);
	}

}
