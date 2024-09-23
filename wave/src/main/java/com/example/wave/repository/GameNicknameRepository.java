package com.example.wave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wave.entity.GameNickname;
import com.example.wave.entity.User;

public interface GameNicknameRepository extends JpaRepository<GameNickname, Long> {
	GameNickname findByNickname(String nickname);

	// 사용자 ID로 GameNickname 목록을 조회하는 메서드
	List<GameNickname> findByUser_UserId(String userId);

	// 사용자로 게임 닉네임 삭제
	void deleteByUser(User user);
}