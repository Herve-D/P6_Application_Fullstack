package com.openclassrooms.mddapi.service;

import java.util.ArrayList;
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

	public PostDto toDto(Post post) {
		return this.modelMapper.map(post, PostDto.class);
	}

	private Post toEntity(PostDto postDto) {
		return this.modelMapper.map(postDto, Post.class);
	}

	public Post createPost(PostRequest postRequest) {
		MddUserDto userDto = this.userService.getCurrentUser();
		TopicDto topicDto = this.topicService.getTopicById(postRequest.getTopic());

		PostDto postDto = new PostDto();
		postDto.setTitle(postRequest.getTitle());
		postDto.setContent(postRequest.getContent());
		postDto.setUser(userDto);
		postDto.setTopic(topicDto);

		return this.postRepository.save(this.toEntity(postDto));
	}

	public void updatePost(PostDto postDto) {
		this.postRepository.save(this.toEntity(postDto));
	}

	public PostDto getPostById(Long id) {
		return this.toDto(this.postRepository.findById(id).orElse(null));
	}

	public List<PostDto> getSubscriptionPosts() {
		MddUserDto userDto = this.userService.getCurrentUser();
		List<TopicDto> topicDtos = userDto.getTopics();

		List<PostDto> postDtos = new ArrayList<>();
		topicDtos.forEach(topicDto -> {
			List<Post> postByTopic = this.postRepository.findByTopic(this.topicService.toEntity(topicDto));
			postDtos.addAll(postByTopic.stream().map(this::toDto).collect(Collectors.toList()));
		});

		return postDtos;
	}

}
