package com.example.findingFlatmates.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.findingFlatmates.Dtos.flatPostsDto;
import com.example.findingFlatmates.service.flatPostsService;

@RestController
@RequestMapping("/posts")
public class PostController {
	private flatPostsService service;
	
	public PostController(flatPostsService service) {
		this.service = service;
	}
	
	@PostMapping("/createPost")
	public flatPostsDto creatingPost(@RequestBody flatPostsDto fdto) {
		return service.createPost(fdto);
	}
}
