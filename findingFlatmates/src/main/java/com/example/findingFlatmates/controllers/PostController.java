package com.example.findingFlatmates.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.findingFlatmates.Dtos.flatPostsDto;
import com.example.findingFlatmates.entities.flatPosts;
import com.example.findingFlatmates.service.flatPostsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
	private flatPostsService service;
	
	public PostController(flatPostsService service) {
		this.service = service;
	}
	
	@PostMapping("/createPost/{userid}")
	public ResponseEntity<flatPostsDto> creatingPost( @Valid @RequestBody flatPostsDto fdto, @PathVariable int userid) {
		return new ResponseEntity<flatPostsDto>(service.createPost(fdto,userid),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletePost/{id}")
	public ResponseEntity<String> deletingPost(@PathVariable int id) {
		service.deletePost(id);
		return new ResponseEntity<String>("id : " + id+" has been deleted", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updatePost/{userid}/{id}")
	public flatPostsDto updatingPost(@Valid @PathVariable int id, @RequestBody flatPostsDto fdto, @PathVariable int userid) {
		return service.updatePost(id, fdto, userid);
	}
	
	@GetMapping("/getallposts")
	public List<flatPostsDto> gettingAllPosts(){
		return service.allPosts();
	}
	
	@GetMapping("/getonepost/{id}")
	public flatPostsDto gettingOnePosts(@PathVariable int id){
		return service.onePostById(id);
	}
	
	@GetMapping("/getpostsbycity/{city}")
	public List<flatPostsDto> gettingCityPosts(@PathVariable String city){
		return service.postsByCity(city,city);
	}
	
	@GetMapping("/getpostbyroom/{room}")
	public List<flatPostsDto> gettingRoomPosts(@PathVariable String room){
		return service.postsByroom(room);
	}
	
	@GetMapping("/getpostbytenent/{tenent}")
	public List<flatPostsDto> gettingtenentPosts(@PathVariable String tenent){
		return service.postsBytenent(tenent);
	}
	
	@GetMapping("/getvariousparameters")
	public List<flatPostsDto> gettingVariousPosts(@RequestParam String city,
			@RequestParam String roomType,
			@RequestParam String tenenttype,
			@RequestParam long price){
		return service.findPosts(city, roomType, tenenttype, price);
	}
	
	@GetMapping("/getSelectiveResults")
	public List<flatPostsDto> getPostReults(@RequestParam(required = false) String city,
			@RequestParam(required = false) String roomType,
			@RequestParam(required = false) String tenenttype,
			@RequestParam(required = false) Long price){
		return service.findSelective(city, roomType, tenenttype, price);
	}
	
	
}
