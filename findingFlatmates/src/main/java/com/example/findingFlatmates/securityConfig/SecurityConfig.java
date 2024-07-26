package com.example.findingFlatmates.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.findingFlatmates.jwtFilter.JwtAuthenticationFilter;
import com.example.findingFlatmates.serviceImpl.UserServiceImpl;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserServiceImpl userDetailsServiceImp;
	private final JwtAuthenticationFilter authenticationFilter;
	
	public SecurityConfig(UserServiceImpl userDetailsServiceImp, JwtAuthenticationFilter authenticationFilter) {
		super();
		this.userDetailsServiceImp = userDetailsServiceImp;
		this.authenticationFilter = authenticationFilter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return  http
				.csrf(AbstractHttpConfigurer::disable)
				
				.authorizeHttpRequests(
						req -> req.requestMatchers("/login/**","/register/**").permitAll().anyRequest().authenticated()
						).userDetailsService(userDetailsServiceImp)
				
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
