package com.example.wave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.wave.dto.GameNicknameDTO;
import com.example.wave.entity.GameNickname;
import com.example.wave.entity.User;
import com.example.wave.repository.GameNicknameRepository;
import com.example.wave.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Log4j2 로깅 기능을 제공하는 어노테이션
@Service // Spring의 서비스 컴포넌트로 등록
@Validated // 유효성 검사를 활성화하는 어노테이션
public class GameService {

	@Autowired
	private GameNicknameRepository gameNicknameRepository;

	@Autowired
	private UserRepository userRepository; // UserRepository 주입
	
	/**
     * 사용자의 게임 닉네임을 저장하는 메서드입니다.
     * @param dto 게임 닉네임 정보를 담고 있는 DTO
     */
	public void saveUserGameNickname(@Valid GameNicknameDTO dto) {
		// userId로 DB에서 사용자 조회
		User user = userRepository.findByUserId(dto.getUserDiscordId());
		
		// GameNickname 엔티티를 빌더 패턴을 사용하여 생성
		GameNickname gameNickname = GameNickname.builder()
				.user(user)
	            .gameName(dto.getGameName())
	            .nickname(dto.getNickname())
	            .build();

		// GameNickname 엔티티를 데이터베이스에 저장
		gameNicknameRepository.save(gameNickname);

	}

}
