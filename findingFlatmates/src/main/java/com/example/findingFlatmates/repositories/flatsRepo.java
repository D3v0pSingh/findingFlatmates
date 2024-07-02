package com.example.findingFlatmates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findingFlatmates.entities.flatPosts;
import java.util.List;


public interface flatsRepo extends JpaRepository<flatPosts, Integer>{
	
	//searching using poster's name here mapping will play 
	List<flatPosts> findById(int userid);
	//searching using city name
	List<flatPosts> findByCity(String city);
	//searching if 1bhk or 2bhk
	List<flatPosts> findByRoomType(String roomType);
	//searching if required for female or male
	List<flatPosts> findByTenenttype(String tenenttype);
	//searching using locality
	List<flatPosts> findByFulladdress(String fulladdress);
	
	
	//one more search if user gives all filter city, room, tenenttype, price

}
