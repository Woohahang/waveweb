package com.example.wave.nickname.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wave.nickname.entity.Nickname;
import com.example.wave.user.entity.User;

public interface NicknameRepository extends JpaRepository<Nickname, Long> {
	// 사용자 ID로 GameNickname 목록을 조회하는 메서드
	List<Nickname> findByUser_UserId(String userId);

	// 사용자로 게임 닉네임 삭제
	void deleteByUser(User user);
	
	 // 같은 게임 내에서 닉네임이 존재하는지 확인
    boolean existsByGameNameAndNickname(String gameName, String nickname);

}