package com.example.wave.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.wave.nickname.repository.NicknameRepository;
import com.example.wave.user.dto.UserDTO;
import com.example.wave.user.entity.User;
import com.example.wave.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Log4j2 로깅 기능을 제공하는 어노테이션
@Service // Spring의 서비스 컴포넌트로 등록
@Validated // 유효성 검사를 활성화하는 어노테이션
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NicknameRepository gameNicknameRepository;
	
	/**
	 * 사용자 정보를 저장하거나 업데이트하는 메서드입니다.
	 * @param userDTO 사용자 정보를 담고 있는 DTO
	 */
	public void saveOrUpdateUser(@Valid UserDTO userDTO) { // @Valid: 유효성 검사를 수행
		try {
			// userId로 DB에서 사용자 조회
			User user = userRepository.findByUserId(userDTO.getDiscordId());
			
			if (user == null) {
				// 사용자가 존재하지 않으면 새로 생성
				createUser(userDTO);
			} else {
				// 사용자가 존재하면 정보를 업데이트
				updateUser(user, userDTO);
			}
		} catch (Exception e) {
			log.error("사용자 생성 중 오류 발생: {}", e.getMessage(), e);
			throw new RuntimeException("사용자 정보 처리 중 오류가 발생했습니다.");
		}
	}
	
	
	/**
	 * 사용자 정보를 삭제하는 메서드입니다.
	 * @param userId 삭제할 사용자 ID
	 */
	@Transactional
	public void deleteUser(String userId) {
	    // 사용자 ID로 사용자 엔티티 조회
	    User user = userRepository.findByUserId(userId);
	    if (user != null) {
	        // 사용자와 관련된 모든 게임 닉네임 삭제
	        gameNicknameRepository.deleteByUser(user);
	        
	        // 사용자 삭제
	        userRepository.delete(user);
	    } else {
	        log.warn("삭제할 사용자가 존재하지 않습니다: {}", userId);
	        throw new RuntimeException("사용자를 찾을 수 없습니다.");
	    }
	}
	

	/**
     * 새 사용자를 생성하는 메서드
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void createUser(@Valid UserDTO userDTO) {
		try {
			User user = User.builder()
					.userId(userDTO.getDiscordId())
		            .username(userDTO.getUserName())
		            .globalName(userDTO.getGlobalName())
		            .build();
			userRepository.save(user);
		} catch (Exception e) {
			log.error("Error occurred while creating user: {}", e.getMessage(), e);
			throw new RuntimeException("사용자 생성 중 오류가 발생했습니다.");
		}
	}

	/**
     * 기존 사용자의 정보를 업데이트하는 메서드
     * @param user 업데이트할 사용자 객체
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void updateUser(User user,@Valid UserDTO userDTO) {
		try {
			user.setUsername(userDTO.getUserName());
			user.setGlobalName(userDTO.getGlobalName());
			userRepository.save(user);
		} catch (Exception e) {
			log.error("사용자 업데이트 중 오류 발생: {}", e.getMessage(), e);
			throw new RuntimeException("사용자 업데이트 중 오류가 발생했습니다.");
		}

	}

}
