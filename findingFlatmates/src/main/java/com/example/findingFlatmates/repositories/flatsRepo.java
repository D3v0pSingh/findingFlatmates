package com.example.findingFlatmates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.findingFlatmates.entities.flatPosts;

import java.util.List;
import java.util.function.Predicate;


public interface flatsRepo extends JpaRepository<flatPosts, Integer>,JpaSpecificationExecutor<flatPosts>{
	List<flatPosts> findAll();
	//searching using poster's name here mapping will play 
	List<flatPosts> findByUserId(int userid);
	//searching using city name
	List<flatPosts> findByCityOrFulladdressContaining(String city, String fulladdress);
	//searching if 1bhk or 2bhk
	List<flatPosts> findByRoomType(String roomType);
	//searching if required for female or male
	List<flatPosts> findByTenenttype(String tenenttype);
	//searching using locality
	List<flatPosts> findByFulladdress(String fulladdress);
	
	//one more search if user gives all filter city, room, tenenttype, price
	List<flatPosts> findByCityAndRoomTypeAndTenenttypeAndPrice(String city, String roomType, String tenenttype, long price);
	

}
