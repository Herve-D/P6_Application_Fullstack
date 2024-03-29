package com.openclassrooms.mddapi.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MddUserDto {

	private Long id;
	private String email;
	private String name;
	private String password;
	private Date created_at;
	private Date updated_at;
	private List<PostDto> posts;
	private List<CommentDto> comments;
	private List<TopicDto> topics;

}
