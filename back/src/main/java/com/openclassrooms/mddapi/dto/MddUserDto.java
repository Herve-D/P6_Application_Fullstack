package com.openclassrooms.mddapi.dto;

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
	private List<TopicDto> topics;

}
