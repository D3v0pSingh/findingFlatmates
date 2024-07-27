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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
	private flatPostsService service;
	
	public PostController(flatPostsService service) {
		this.service = service;
	}
	
	@PostMapping("/createPost/{userid}")
	@Operation(
			tags = "PostDto - Create Ad Api",
			description = "This api will create a new ad and the data will be saved in db.",
			parameters = {
					@Parameter(
							name = "userid",
							required = true,
							description = "There is a mapping between ads table and user table. So to keep track as to how many ads a user has posted will save user details as well while creating a new ad."
					)
			}
		)
	public ResponseEntity<flatPostsDto> creatingPost( @Valid @RequestBody flatPostsDto fdto, @PathVariable int userid) {
		return new ResponseEntity<flatPostsDto>(service.createPost(fdto,userid),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletePost/{id}")
	@Operation(
			tags = "PostDto - Delete Ad Api",
			description = "This api will delete an ad."
			)
	public ResponseEntity<String> deletingPost(@PathVariable int id) {
		service.deletePost(id);
		return new ResponseEntity<String>("id : " + id+" has been deleted", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updatePost/{userid}/{id}")
	@Operation(
			tags = "PostDto - Update Ad Api",
			description = "This api will update an existing ad and the data will be saved in db.",
			parameters = {
					@Parameter(
							name = "userid",
							required = true,
							description = "There is a mapping between ads table and user table. So to keep track as to how many ads a user has posted will save user details as well while creating a new ad."
					)
			}
		)
	public flatPostsDto updatingPost(@Valid @PathVariable int id, @RequestBody flatPostsDto fdto, @PathVariable int userid) {
		return service.updatePost(id, fdto, userid);
	}
	
	@GetMapping("/getallposts")
	@Operation(
			tags = "PostDto - Fetch All Ad Api",
			description = "This api will fetch all ads present in db. This can mostly be used to show the user when they are logging in irrespective of any filter."
		)
	public List<flatPostsDto> gettingAllPosts(){
		return service.allPosts();
	}
	
	@GetMapping("/getonepost/{id}")
	@Operation(
			tags = "PostDto - Waste Api (DO NOT USE THIS)"	
		)
	public flatPostsDto gettingOnePosts(@PathVariable int id){
		return service.onePostById(id);
	}
	
	@GetMapping("/getpostsbycity/{city}")
	@Operation(
			tags = "PostDto - Waste Api (DO NOT USE THIS)"	
		)
	public List<flatPostsDto> gettingCityPosts(@PathVariable String city){
		return service.postsByCity(city,city);
	}
	
	@GetMapping("/getpostbyroom/{room}")
	@Operation(
			tags = "PostDto - Waste Api (DO NOT USE THIS)"	
		)
	public List<flatPostsDto> gettingRoomPosts(@PathVariable String room){
		return service.postsByroom(room);
	}
	
	@GetMapping("/getpostbytenent/{tenent}")
	@Operation(
			tags = "PostDto - Waste Api (DO NOT USE THIS)"	
		)
	public List<flatPostsDto> gettingtenentPosts(@PathVariable String tenent){
		return service.postsBytenent(tenent);
	}
	
	@GetMapping("/getvariousparameters")
	@Operation(
			tags = "PostDto - Waste Api (DO NOT USE THIS)"	
		)
	public List<flatPostsDto> gettingVariousPosts(@RequestParam String city,
			@RequestParam String roomType,
			@RequestParam String tenenttype,
			@RequestParam long price){
		return service.findPosts(city, roomType, tenenttype, price);
	}
	
	@GetMapping("/getSelectiveResults")
	@Operation(
			tags = "PostDto - Fetch Ads using Search/Filters.",
			description = "This api will fetch all ads according to the filters applied by the user.",
			parameters = {
					@Parameter(
							name = "city"
					),
					@Parameter(
							name = "roomType",
							description = "roomType means size of the room"
					),
					@Parameter(
							name = "tenenttype",
							description = "tenentType means no. of tenents required to share a flat."
					),
					@Parameter(
							name = "price"
					)
			}
		)
	public List<flatPostsDto> getPostReults(@RequestParam(required = false) String city,
			@RequestParam(required = false) String roomType,
			@RequestParam(required = false) String tenenttype,
			@RequestParam(required = false) Long price){
		return service.findSelective(city, roomType, tenenttype, price);
	}
	
	
}
