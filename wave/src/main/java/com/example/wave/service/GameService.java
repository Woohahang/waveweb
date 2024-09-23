package com.example.wave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wave.dto.GameNicknameDTO;
import com.example.wave.entity.GameNickname;
import com.example.wave.entity.User;
import com.example.wave.mapper.NicknameMapper;
import com.example.wave.repository.GameNicknameRepository;
import com.example.wave.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GameService {

	@Autowired
	private GameNicknameRepository gameNicknameRepository;

	@Autowired
	private UserRepository userRepository; // UserRepository 주입
	
	public void saveUserGameNickname(GameNicknameDTO dto) {
		User user = userRepository.findByUserId(dto.getUserDiscordId());
		
		   GameNickname gameNickname = GameNickname.builder()
	                .user(user)
	                .gameName(dto.getGameName())
	                .nickname(dto.getNickname())
	                .build();

		// GameNickname 저장
		gameNicknameRepository.save(gameNickname);

	}

}
