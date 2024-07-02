package com.example.findingFlatmates.service;

import java.util.List;

import com.example.findingFlatmates.Dtos.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto udto);
	UserDto updateUser(UserDto udto, int id);
	void deleteUser(int id);
	List<UserDto> getAllUser();
	UserDto getUser(int id);
	

}
