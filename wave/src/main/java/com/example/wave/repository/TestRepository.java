package com.example.wave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wave.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

	Test findByUser_UserId(String discordId);
	
}