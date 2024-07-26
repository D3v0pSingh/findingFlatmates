package com.example.findingFlatmates.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.findingFlatmates.Dtos.UserDto;
import com.example.findingFlatmates.entities.AuthenticationResponse;
import com.example.findingFlatmates.entities.User;
import com.example.findingFlatmates.serviceImpl.AuthenticationService;


@RestController
public class AuthenticationController {
	
private final AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody UserDto request){
		System.out.println("Login is successful");
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
