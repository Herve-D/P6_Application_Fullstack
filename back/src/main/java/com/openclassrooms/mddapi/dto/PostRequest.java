package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class PostRequest {

	private Long topic;
	private String title;
	private String content;

}
