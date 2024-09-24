package com.example.wave.nickname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.wave.nickname.dto.NicknameDTO;
import com.example.wave.nickname.entity.GameNickname;
import com.example.wave.nickname.repository.NicknameRepository;
import com.example.wave.user.entity.User;
import com.example.wave.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Log4j2 로깅 기능을 제공하는 어노테이션
@Service // Spring의 서비스 컴포넌트로 등록
@Validated // 유효성 검사를 활성화하는 어노테이션
public class NicknameService {

	@Autowired
	private NicknameRepository nicknameRepository;

	@Autowired
	private UserRepository userRepository;
	
	/**
     * 사용자의 게임 닉네임을 저장하는 메서드입니다.
     * @param dto 게임 닉네임 정보를 담고 있는 DTO
     */
	public void saveNickname(@Valid NicknameDTO dto) {
		// userId로 DB에서 사용자 조회
		User user = userRepository.findByUserId(dto.getUserDiscordId());
		
		// GameNickname 엔티티를 빌더 패턴을 사용하여 생성
		GameNickname gameNickname = GameNickname.builder()
				.user(user)
	            .gameName(dto.getGameName())
	            .nickname(dto.getNickname())
	            .build();

		// GameNickname 엔티티를 데이터베이스에 저장
		nicknameRepository.save(gameNickname);
	}
	

	/**
     * 사용자 ID에 대한 게임 닉네임 목록을 조회하는 메서드입니다.
     * @param userId 사용자 ID
     * @return 해당 사용자의 게임 닉네임 목록
     */
	public List<GameNickname> getNicknames(String userId) {
		// 사용자 ID로 GameNickname 목록을 조회
		return nicknameRepository.findByUser_UserId(userId);
	}


	/**
	 * 선택된 닉네임 ID 목록을 기반으로 게임 닉네임을 삭제하는 메서드입니다.
	 * @param ids 삭제할 닉네임의 ID 목록
	 */
	public void deleteNickname(List<Long> ids) {
		// 각 닉네임 ID에 대해 삭제 작업 수행
		for (Long id : ids) {
			nicknameRepository.deleteById(id); // 각 닉네임 ID로 삭제
		}
	}

}
