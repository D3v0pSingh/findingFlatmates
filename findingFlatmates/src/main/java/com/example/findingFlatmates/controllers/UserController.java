package com.example.findingFlatmates.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.findingFlatmates.Dtos.UserDto;
import com.example.findingFlatmates.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/postUser")
	public UserDto creatingUser(@RequestBody UserDto udto) {
		return service.createUser(udto);
	}
	
	@PutMapping("/updateUser/{id}")
	public UserDto updatingUser(@RequestBody UserDto udto, @PathVariable int id) {
		return service.updateUser(udto, id);
	}
	
	@GetMapping("/getAllUsers")
	public List<UserDto> allUser(){
		return service.getAllUser();
	}
	
	@GetMapping("/getUser/{id}")
	public UserDto singleUser(@PathVariable int id) {
		return service.getUser(id);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUser(id);
	}
	
}
