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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/postUser")
	@Operation(
			tags = "UserDto - Saving User Api(Do not use as the same thing is done by Register Api)",
			description = "This api will register the user and save the details in our db."
		)
	public UserDto creatingUser(@Valid @RequestBody UserDto udto) {
		return service.createUser(udto);
	}
	
	@PutMapping("/updateUser/{id}")
	@Operation(
			tags = "UserDto - Update User Api",
			description = "This api will update the existing user and save the details in our db.",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "Since Id is primary key in user db so we would need to fetch id of the logged in user to update the profile."
					)
			}
		)
	public UserDto updatingUser(@Valid @RequestBody UserDto udto, @PathVariable int id) {
		return service.updateUser(udto, id);
	}
	
	@GetMapping("/getAllUsers")
	@Operation(
			tags = "UserDto - Fetching All Users.(Not Important)",
			description = "This api will fetch all our users present in our db. Not Important. Will be used if we make admin application."
		)
	public List<UserDto> allUser(){
		return service.getAllUser();
	}
	
	@GetMapping("/getUser/{id}")
	@Operation(
			tags = "UserDto - Fetching A User.(Not Important)",
			description = "This api will fetch a user present in our db. Not Important. Will be used if we make admin application.",
			parameters = {
					@Parameter(
						name = "id",
						required = true,
					description = "Since Id is primary key in user db so we would need to fetch id of the logged in user."
				)
			}
		)
	public UserDto singleUser(@PathVariable int id) {
		return service.getUser(id);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	@Operation(
			tags = "UserDto - Deleting A User.(Not Important)",
			description = "This api will fetch a user present in our db. Not Important. Will be used if we make admin application."
		)
	public void deleteUser(@PathVariable int id) {
		service.deleteUser(id);
	}
	
}
