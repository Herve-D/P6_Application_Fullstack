package com.openclassrooms.mddapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {

	private Long id;
	private String name;
	private String content;
	private List<PostDto> posts;
	private List<MddUserDto> users;

}
