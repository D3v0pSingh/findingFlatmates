package com.example.findingFlatmates.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findingFlatmates.Dtos.flatPostsDto;
import com.example.findingFlatmates.entities.User;
import com.example.findingFlatmates.entities.flatPosts;
import com.example.findingFlatmates.repositories.UserRepo;
import com.example.findingFlatmates.repositories.flatsRepo;
import com.example.findingFlatmates.service.flatPostsService;
@Service
public class flatPostsServiceImpl implements flatPostsService {
	
	private flatsRepo repo;
	private ModelMapper mm;
	private UserRepo userRepo;
	
	@Autowired
	public flatPostsServiceImpl(flatsRepo repo,ModelMapper mm,UserRepo userRepo) {
		this.repo = repo;
		this.mm = mm;
		this.userRepo = userRepo;
	}

	@Override
	public flatPostsDto createPost(flatPostsDto fdto) {
		flatPosts p = mm.map(fdto, flatPosts.class);
		return mm.map(repo.save(p), flatPostsDto.class);
	}

	@Override
	public flatPostsDto updatePost(int id, flatPostsDto fdto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<flatPostsDto> allPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public flatPostsDto onePostById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<flatPostsDto> postsByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<flatPostsDto> postsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<flatPostsDto> postsByroom(String rt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<flatPostsDto> postsBytenent(String tt) {
		// TODO Auto-generated method stub
		return null;
	}

}
