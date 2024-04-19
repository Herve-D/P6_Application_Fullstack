package com.openclassrooms.mddapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.openclassrooms.mddapi.security.JwtFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	/**
	 * Configure the security filter chain.
	 * 
	 * @param http - The HttpSecurity object to configure.
	 * @return The configured SecurityFilterChain object.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	/**
	 * Creates a CorsFilter bean to configure CORS settings.
	 * 
	 * @return The configured CorsFilter.
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	/**
	 * Creates a PasswordEncoder bean to encode passwords.
	 * 
	 * @return The PasswordEncoder bean.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Creates an AuthenticationManager bean.
	 * 
	 * @param http                  - The HttpSecurity object.
	 * @param bCryptPasswordEncoder - The BCryptPasswordEncoder.
	 * @param userDetailsService    - The CustomUserDetailsService.
	 * @return The AuthenticationManager bean.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}

}
