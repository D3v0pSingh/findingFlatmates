package com.example.findingFlatmates.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.findingFlatmates.Dtos.flatPostsDto;
import com.example.findingFlatmates.entities.User;
import com.example.findingFlatmates.entities.flatPosts;
import com.example.findingFlatmates.exceptions.ResourceNotFoundException;
import com.example.findingFlatmates.repositories.UserRepo;
import com.example.findingFlatmates.repositories.flatsRepo;
import com.example.findingFlatmates.service.flatPostsService;
import com.example.findingFlatmates.specifications.SearchSpecification;
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
	public flatPostsDto createPost(flatPostsDto fdto, int userid) {
		User user = userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		flatPosts p = mm.map(fdto, flatPosts.class);
		p.setUser(user);
		return mm.map(repo.save(p), flatPostsDto.class);
	}

	@Override
	public flatPostsDto updatePost(int id, flatPostsDto fdto, int userid) {
		User user = userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		flatPosts p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		p.setPrice(fdto.getPrice());
		p.setDesc(fdto.getDesc());
		p.setPtitle(fdto.getPtitle());
		p.setPpics(fdto.getPpics());
		p.setCity(fdto.getCity());
		p.setFulladdress(fdto.getFulladdress());
		p.setTenenttype(fdto.getTenenttype());
		p.setNoofperson(fdto.getNoofperson());
		p.setRoomType(fdto.getRoomType());
		p.setUser(user);
		
		flatPosts updatedPost = repo.save(p);
		return mm.map(updatedPost, flatPostsDto.class);
	}

	@Override
	public void deletePost(int id) {
		flatPosts p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		repo.delete(p);

	}

	@Override
	public List<flatPostsDto> allPosts() {
		List<flatPosts> list = repo.findAll();
		List<flatPostsDto> allList = list.stream().map(i->mm.map(i, flatPostsDto.class)).collect(Collectors.toList());
		return allList;
	}

	@Override
	public flatPostsDto onePostById(int id) {
		flatPosts p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mm.map(p, flatPostsDto.class);
	}

	@Override
	public List<flatPostsDto> postsByUsername(String userName) {
		
		return null;
	}

	@Override
	public List<flatPostsDto> postsByCity(String city, String city1) {
		List<flatPosts> pp = repo.findAll();
		List<flatPosts> p = repo.findByCityOrFulladdressContaining(city, city1);
		List<flatPostsDto> list = p.stream().map(i->mm.map(i, flatPostsDto.class)).collect(Collectors.toList());

		return list;
	}

	@Override
	public List<flatPostsDto> postsByroom(String rt) {
		List<flatPosts> p  = repo.findByRoomType(rt);
		List<flatPostsDto> list = p.stream().map(i->mm.map(i,flatPostsDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<flatPostsDto> postsBytenent(String tt) {
		List<flatPosts> p = repo.findByTenenttype(tt);
		List<flatPostsDto> list = p.stream().map(i->mm.map(i, flatPostsDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<flatPostsDto> findPosts(String city, String roomType, String tenenttype, Long price) {
		List<flatPosts> p = repo.findByCityAndRoomTypeAndTenenttypeAndPrice(city, roomType, tenenttype, price);
		List<flatPostsDto> list = p.stream().map(i->mm.map(i, flatPostsDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<flatPostsDto> findSelective(String city, String roomType, String tenenttype, Long price) {
		Specification<flatPosts> specification = SearchSpecification.withDynamicQuery(city, roomType, tenenttype, price);
		List<flatPosts> allList = repo.findAll(specification);
		List<flatPostsDto> allListDto = allList.stream().map(i->mm.map(i, flatPostsDto.class)).collect(Collectors.toList());
		return allListDto;
	}

}
