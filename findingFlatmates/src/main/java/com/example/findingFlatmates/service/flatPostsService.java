package com.example.findingFlatmates.service;

import java.util.List;

import com.example.findingFlatmates.Dtos.flatPostsDto;

public interface flatPostsService {
	
	flatPostsDto createPost(flatPostsDto fdto);
	
	flatPostsDto updatePost(int id, flatPostsDto fdto);
	
	void deletePost(int id);
	
	List<flatPostsDto> allPosts();
	
	flatPostsDto onePostById(int id);
	
	//searching using poster's name
	List<flatPostsDto> postsByUserId(int userId);
	//city
	List<flatPostsDto> postsByCity(String city);
	//room 
	List<flatPostsDto> postsByroom(String rt);
	//tenent
	List<flatPostsDto> postsBytenent(String tt);
	
	
	
	

}
