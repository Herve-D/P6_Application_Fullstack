package com.openclassrooms.mddapi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.MddUserDto;
import com.openclassrooms.mddapi.model.MddUser;
import com.openclassrooms.mddapi.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	private MddUserDto toDto(MddUser user) {
		return modelMapper.map(user, MddUserDto.class);
	}

	private MddUser toEntity(MddUserDto userDto) {
		return modelMapper.map(userDto, MddUser.class);
	}

	public MddUserDto getUserById(Long id) {
		return toDto(userRepository.findById(id).orElse(null));
	}

	public void updateUser(Long id, MddUserDto user) {
		user.setId(id);
		this.userRepository.save(toEntity(user));
	}

	public MddUserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		Optional<MddUser> userOptional = this.userRepository.findByEmail(authentication.getName());
		if (userOptional.isPresent()) {
			return toDto(userOptional.get());
		}
		return null;
	}

}
