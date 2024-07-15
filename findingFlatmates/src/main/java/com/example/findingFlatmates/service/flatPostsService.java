package com.example.findingFlatmates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.example.findingFlatmates.Dtos.flatPostsDto;
import com.example.findingFlatmates.entities.flatPosts;
import com.example.findingFlatmates.repositories.flatsRepo;
import com.example.findingFlatmates.specifications.SearchSpecification;

public interface flatPostsService {
	
	
	flatPostsDto createPost(flatPostsDto fdto, int userid);
	
	flatPostsDto updatePost(int id, flatPostsDto fdto, int userid);
	
	void deletePost(int id);
	
	List<flatPostsDto> allPosts();
	
	flatPostsDto onePostById(int id);
	
	//searching using poster's name
	List<flatPostsDto> postsByUsername(String username);//ask ricky bhaiya
	//city
	List<flatPostsDto> postsByCity(String city, String city1);
	//room 
	List<flatPostsDto> postsByroom(String rt);
	//tenent
	List<flatPostsDto> postsBytenent(String tt);
	
	List<flatPostsDto> findPosts(String city, String roomType, String tenenttype, Long price);
	
	List<flatPostsDto> findSelective(String city, String roomType, String tenenttype, Long price);
	
	
	
	

}
