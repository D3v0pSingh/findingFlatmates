package com.example.findingFlatmates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findingFlatmates.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
