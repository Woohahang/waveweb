package com.example.wave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wave.entity.GameNickname;

public interface GameNicknameRepository extends JpaRepository<GameNickname, Long> {
	GameNickname findByNickname(String nickname);
}