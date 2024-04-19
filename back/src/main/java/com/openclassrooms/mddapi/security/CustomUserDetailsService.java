package com.openclassrooms.mddapi.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.MddUser;
import com.openclassrooms.mddapi.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	/**
	 * Load user details by username.
	 * 
	 * @param username - The username of the user to load.
	 * @return UserDetails object containing user details.
	 * @throws UsernameNotFoundException If the user with the given username is not
	 *                                   found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MddUser> userOptional = userRepository.findByEmail(username);
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("");
		}
		MddUser user = userOptional.get();
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getGrantedAuthorities());
	}

	/**
	 * Get the granted authorities for the user.
	 * 
	 * @return List of GrantedAuthority objects representing the user's roles.
	 */
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}
