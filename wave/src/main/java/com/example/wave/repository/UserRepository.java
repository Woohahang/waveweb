package com.example.wave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wave.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

}