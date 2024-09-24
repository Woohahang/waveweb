package com.example.wave.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wave.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

}