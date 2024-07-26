package com.example.findingFlatmates.serviceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.findingFlatmates.Dtos.UserDto;
import com.example.findingFlatmates.entities.AuthenticationResponse;
import com.example.findingFlatmates.entities.User;

import com.example.findingFlatmates.exceptions.ResourceNotFoundException;
import com.example.findingFlatmates.repositories.UserRepo;

@Service
public class AuthenticationService {
	private final UserRepo repository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JWTServiceImpl jwtService;
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepo repository, PasswordEncoder passwordEncoder, JWTServiceImpl jwtService,
			AuthenticationManager authenticationManager) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	public AuthenticationResponse register(UserDto request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user.setName(request.getName());
		user.setPhoneNo(request.getPhoneNo());
		user.setProfilePic(request.getProfilePic());
		
		user = repository.save(user);
		
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
		
	}
	
	public AuthenticationResponse authenticate(UserDto request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = repository.findByEmail(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("username", "email : "+request.getUsername(), 0));
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
		
	}
	
}
