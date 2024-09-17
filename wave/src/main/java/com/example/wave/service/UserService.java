package com.example.wave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wave.dto.UserDTO;
import com.example.wave.entity.User;
import com.example.wave.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 사용자 정보를 저장하거나 업데이트하는 메서드입니다.
	 * @param userDTO 사용자 정보를 담고 있는 DTO
	 */
	public void saveOrUpdateUser(UserDTO userDTO) {
			// userId로 사용자 조회
			User user = userRepository.findByUserId(userDTO.getUserId());

			if (user == null) {
				// 사용자가 존재하지 않으면 새로 생성
				createUser(userDTO);
			} else {
				// 사용자가 존재하면 정보를 업데이트
				updateUser(user, userDTO);
			}
		}

	/**
     * 새 사용자를 생성하는 메서드
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void createUser(UserDTO userDTO) {
		User user = User.builder()
				.userId(userDTO.getUserId())
	            .username(userDTO.getUsername())
	            .globalName(userDTO.getGlobalName())
	            .locale(userDTO.getLocale())
	            .build();
		userRepository.save(user);
	}

	/**
     * 기존 사용자의 정보를 업데이트하는 메서드
     * @param user 업데이트할 사용자 객체
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void updateUser(User user, UserDTO userDTO) {
		user.setUsername(userDTO.getUsername());
		user.setGlobalName(userDTO.getGlobalName());
		user.setLocale(userDTO.getLocale());
		userRepository.save(user);
	}

}
