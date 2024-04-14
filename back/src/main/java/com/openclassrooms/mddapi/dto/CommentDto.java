package com.openclassrooms.mddapi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private Long id;
	private String content;
	private Date created_at;
	private MddUserDto user;

}
