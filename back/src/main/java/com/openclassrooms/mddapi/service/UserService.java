package com.openclassrooms.mddapi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.MddUserDto;
import com.openclassrooms.mddapi.model.MddUser;
import com.openclassrooms.mddapi.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	public MddUserDto toDto(MddUser user) {
		return this.modelMapper.map(user, MddUserDto.class);
	}

	private MddUser toEntity(MddUserDto userDto) {
		return this.modelMapper.map(userDto, MddUser.class);
	}

	public MddUserDto getUserById(Long id) {
		return this.toDto(this.userRepository.findById(id).orElse(null));
	}

	public MddUser updateUser(Long id, MddUserDto user) {
		MddUserDto userDto = getUserById(id);
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(this.toEntity(userDto));
	}

	public MddUserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		Optional<MddUser> userOptional = this.userRepository.findByEmail(authentication.getName());
		if (userOptional.isPresent()) {
			return this.toDto(userOptional.get());
		}
		return null;
	}

}
