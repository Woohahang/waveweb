package com.example.wave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wave.dto.UserDTO;
import com.example.wave.entity.User;
import com.example.wave.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 사용자 정보를 저장하거나 업데이트하는 메서드입니다.
	 * @param userDTO 사용자 정보를 담고 있는 DTO
	 */
	public void saveOrUpdateUser(UserDTO userDTO) {
		try {
			// userId로 DB에서 사용자 조회
			User user = userRepository.findByUserId(userDTO.getUserId());

			if (user == null) {
				// 사용자가 존재하지 않으면 새로 생성
				createUser(userDTO);
			} else {
				// 사용자가 존재하면 정보를 업데이트
				updateUser(user, userDTO);
			}
		} catch (Exception error) {
			log.error("사용자 생성 중 오류 발생: {}", error.getMessage(), error);
			throw new RuntimeException("사용자 정보 처리 중 오류가 발생했습니다.");
		}
	}

	/**
     * 새 사용자를 생성하는 메서드
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void createUser(UserDTO userDTO) {
		try {
			User user = User.builder()
					.userId(userDTO.getUserId())
		            .username(userDTO.getUsername())
		            .globalName(userDTO.getGlobalName())
		            .locale(userDTO.getLocale())
		            .build();
			userRepository.save(user);
		} catch (Exception error) {
			log.error("Error occurred while creating user: {}", error.getMessage(), error);
			throw new RuntimeException("사용자 생성 중 오류가 발생했습니다.");
		}
	}

	/**
     * 기존 사용자의 정보를 업데이트하는 메서드
     * @param user 업데이트할 사용자 객체
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void updateUser(User user, UserDTO userDTO) {
		try {
			user.setUsername(userDTO.getUsername());
			user.setGlobalName(userDTO.getGlobalName());
			user.setLocale(userDTO.getLocale());
			userRepository.save(user);			
		} catch (Exception error) {
			log.error("사용자 업데이트 중 오류 발생: {}", error.getMessage(), error);
			throw new RuntimeException("사용자 업데이트 중 오류가 발생했습니다.");
		}

	}

}
