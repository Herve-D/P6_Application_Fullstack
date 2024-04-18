package com.openclassrooms.mddapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.CommentRequest;
import com.openclassrooms.mddapi.dto.MddUserDto;
import com.openclassrooms.mddapi.dto.PostDto;

@Service
public class CommentService {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	public void createComment(CommentRequest commentRequest) {
		MddUserDto userDto = this.userService.getUserById(commentRequest.getUserId());
		PostDto postDto = this.postService.getPostById(commentRequest.getPostId());

		CommentDto commentDto = new CommentDto();
		commentDto.setContent(commentRequest.getContent());
		commentDto.setUser(userDto);

		postDto.getComments().add(commentDto);
		this.postService.updatePost(postDto);
	}

}
