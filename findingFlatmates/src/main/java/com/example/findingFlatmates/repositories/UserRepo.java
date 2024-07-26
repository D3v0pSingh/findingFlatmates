package com.example.findingFlatmates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findingFlatmates.entities.User;
import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email); 
}
