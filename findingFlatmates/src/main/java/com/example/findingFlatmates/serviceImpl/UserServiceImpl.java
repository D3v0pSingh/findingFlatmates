package com.example.findingFlatmates.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.findingFlatmates.Dtos.UserDto;
import com.example.findingFlatmates.entities.User;
import com.example.findingFlatmates.exceptions.ResourceNotFoundException;
import com.example.findingFlatmates.repositories.UserRepo;
import com.example.findingFlatmates.service.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
	@Autowired
	private UserRepo repo;
	@Autowired
	private ModelMapper mm;
	
	
	@Override
	public UserDto createUser(UserDto udto) {
		User cUser = mm.map(udto, User.class);
		User createdUser = repo.save(cUser);
		return mm.map(createdUser, UserDto.class);
		
	}

	@Override
	public UserDto updateUser(UserDto udto, int id) {
		User gettingUser = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		gettingUser.setEmail(udto.getEmail());
		gettingUser.setName(udto.getName());
		gettingUser.setPhoneNo(udto.getPhoneNo());
		gettingUser.setProfilePic(udto.getProfilePic());
		User updatedUser = repo.save(gettingUser);
		return mm.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(int id) {
		User gettingUser = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		repo.delete(gettingUser);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list =  repo.findAll();
		List<UserDto> dtolist = list.stream().map(i -> mm.map(i, UserDto.class)).collect(Collectors.toList());
		return dtolist;
	}

	@Override
	public UserDto getUser(int id) {
		User gettingUser = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		return mm.map(gettingUser, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  repo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", username, 0));
		return mm.map(user, UserDto.class);
	}

}
