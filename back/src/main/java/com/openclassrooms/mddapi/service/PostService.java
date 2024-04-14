package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.MddUserDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRequest;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.IPostRepository;

@Service
public class PostService {

	@Autowired
	private IPostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private ModelMapper modelMapper;

	private PostDto toDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}

	private Post toEntity(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}

	public void createPost(PostRequest postRequest) {
		MddUserDto userDto = this.userService.getCurrentUser();
		TopicDto topicDto = this.topicService.getTopicById(postRequest.getTopic());

		PostDto postDto = new PostDto();
		postDto.setTitle(postRequest.getTitle());
		postDto.setContent(postRequest.getContent());
		postDto.setUser(userDto);
		postDto.setTopic(topicDto);

		this.postRepository.save(toEntity(postDto));
	}

	public List<PostDto> getPosts() {
		return postRepository.findAll()
				.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

}
