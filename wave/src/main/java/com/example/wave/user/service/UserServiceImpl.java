package com.example.wave.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.wave.exception.UserServiceException;
import com.example.wave.nickname.repository.NicknameRepository;
import com.example.wave.user.dto.UserDto;
import com.example.wave.user.entity.User;
import com.example.wave.user.mapper.UserMapper;
import com.example.wave.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Log4j2 로깅 기능을 제공하는 어노테이션
@Service // Spring의 서비스 컴포넌트로 등록
@Validated // 유효성 검사를 활성화하는 어노테이션
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NicknameRepository nicknameRepository;
	
	/**
	 * 사용자 정보를 저장하거나 업데이트하는 메서드입니다.
	 * @param userDTO 사용자 정보를 담고 있는 DTO
	 */
	@Override
	public void saveOrUpdateUser(@Valid UserDto userDto) { // @Valid: 유효성 검사를 수행
			// userId로 DB에서 사용자 조회
			User user = userRepository.findByUserId(userDto.getDiscordId());
			
			if (user == null) {
				// 사용자가 존재하지 않으면 새로 생성
				createUser(userDto);
			} else {
				// 사용자가 존재하면 정보를 업데이트
				updateUser(user, userDto);
			}
	}

	
	/**
	 * 사용자 정보를 삭제하는 메서드입니다.
	 * @param userId 삭제할 사용자 ID
	 */
	@Override
	@Transactional
	public void deleteUser(String userId) {
		// 사용자 ID로 사용자 엔티티 조회
		User user = userRepository.findByUserId(userId);

		if (user == null) {
			throw new UserServiceException("사용자를 찾을 수 없습니다.");
		}

		try {
			// 사용자와 관련된 닉네임 모두 삭제, 이후 사용자 삭제
			nicknameRepository.deleteByUser(user);
			userRepository.delete(user);

		} catch (DataIntegrityViolationException e) {
			throw new UserServiceException("사용자를 삭제하는 중 데이터 무결성 오류가 발생했습니다.");
		}
	}


	/**
     * 새 사용자를 생성하는 메서드
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void createUser(@Valid UserDto userDTO) {
		try {
			User user = UserMapper.toEntity(userDTO);
			userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new UserServiceException("사용자 생성 중 오류가 발생했습니다.");
		}
	}

	/**
     * 기존 사용자의 정보를 업데이트하는 메서드
     * @param user 업데이트할 사용자 객체
     * @param userDTO 사용자 정보를 담고 있는 DTO
     */
	private void updateUser(User user,@Valid UserDto userDto) {
		try {
			user.setUsername(userDto.getUserName());
			user.setGlobalName(userDto.getGlobalName());
			userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new UserServiceException("사용자 업데이트 중 오류가 발생했습니다.");
		}

	}

}
