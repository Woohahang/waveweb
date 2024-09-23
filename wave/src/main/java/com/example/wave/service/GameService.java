package com.example.wave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wave.dto.GameNicknameDTO;
import com.example.wave.entity.GameNickname;
import com.example.wave.entity.User;
import com.example.wave.repository.GameNicknameRepository;
import com.example.wave.repository.TestRepository;
import com.example.wave.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GameService {

	@Autowired
	private GameNicknameRepository gameNicknameRepository;

	@Autowired
	private UserRepository userRepository; // UserRepository 주입

	@Autowired
	private TestRepository testRepository;

	public void saveUserGameNickname(GameNicknameDTO dto) {
		User user = userRepository.findByUserId(dto.getUserDiscordId());

		GameNickname gameNickname = new GameNickname();
		gameNickname.setUser(user);
		gameNickname.setGameType(dto.getGameName());
		gameNickname.setNickname(dto.getNickname());
				
		 // GameNickname 저장
        gameNicknameRepository.save(gameNickname);

	}

	// User 테이블의 userId를 Test 엔티티에 넣어서 저장
	public void test(String userId) {
		User user = userRepository.findByUserId(userId);

		GameNickname gameNickname = new GameNickname();
		gameNickname.setUser(user); // User 설정
		gameNickname.setNickname("끼매누"); // 닉네임 설정
		gameNickname.setGameType("리그오브레전드");
		
		
		 // GameNickname 저장
        gameNicknameRepository.save(gameNickname);

	}

}
