package com.example.findingFlatmates.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.findingFlatmates.Dtos.UserDto;
import com.example.findingFlatmates.entities.AuthenticationResponse;
import com.example.findingFlatmates.entities.User;
import com.example.findingFlatmates.serviceImpl.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class AuthenticationController {
	
private final AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	@Operation(
			tags = "Auth - Register User Api",
			description = "This api will register the user and save the details in our db."
		)
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")
	@Operation(
			tags = "Auth - Login User Api",
			description = "This api will login the user using email and password being fetched from Db.<br> After Successful login we will get a token which is being used as a authentication parameter."
		)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody UserDto request){
		System.out.println("Login is successful");
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
