package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.IPostRepository;

@Service
public class PostService {

	@Autowired
	private IPostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	private PostDto toDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}

	private Post toEntity(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}

	public List<PostDto> getPosts() {
		return postRepository.findAll().stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

}
