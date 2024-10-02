package com.example.wave.nickname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.wave.exception.NicknameException;
import com.example.wave.nickname.dto.NicknameDto;
import com.example.wave.nickname.entity.GameNickname;
import com.example.wave.nickname.repository.NicknameRepository;
import com.example.wave.user.entity.User;
import com.example.wave.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Log4j2 로깅 기능을 제공하는 어노테이션
@Service // Spring의 서비스 컴포넌트로 등록
@Validated // 유효성 검사를 활성화하는 어노테이션
public class NicknameServiceImpl implements NicknameService {

	@Autowired
	private NicknameRepository nicknameRepository;

	@Autowired
	private UserRepository userRepository;
	
	/**
     * 사용자의 게임 닉네임을 저장하는 메서드입니다.
     * @param dto 게임 닉네임 정보를 담고 있는 DTO
     */
	@Override
	public void saveNickname(@Valid NicknameDto dto) {
		  try {
		        // userId로 DB에서 사용자 조회
		        User user = userRepository.findByUserId(dto.getUserDiscordId());
		        if (user == null) {
		            throw new NicknameException("사용자를 찾을 수 없습니다. :" + dto.getUserDiscordId());
		        }
		        
		        // 같은 게임 내에서 닉네임 중복 검사
		        if (checkNicknameExists(dto.getGameName(), dto.getNickname())) {
		            throw new NicknameException("닉네임이 이미 존재합니다: " + dto.getNickname());
		        }

		        // GameNickname 엔티티를 빌더 패턴을 사용하여 생성
		        GameNickname gameNickname = GameNickname.builder()
		                .user(user)
		                .gameName(dto.getGameName())
		                .nickname(dto.getNickname())
		                .build();

				// GameNickname 엔티티를 데이터베이스에 저장
				nicknameRepository.save(gameNickname);
			} catch (NicknameException ne) {
				// 닉네임 관련 예외는 그대로 던져줌
				throw ne;
			} catch (Exception e) {
				throw new NicknameException("닉네임 저장 중 오류가 발생했습니다. :", e);
			}
		}
	

	/**
     * 사용자 ID에 대한 게임 닉네임 목록을 조회하는 메서드입니다.
     * @param userId 사용자 ID
     * @return 해당 사용자의 게임 닉네임 목록
     */
	@Override
	public List<GameNickname> getNicknames(String userId) {
		try {
			// 사용자 ID로 GameNickname 목록을 조회
			return nicknameRepository.findByUser_UserId(userId);
		} catch (Exception e) {
			throw new NicknameException("닉네임 조회 중 오류가 발생했습니다. :", e);
		}
	}


	/**
	 * 선택된 닉네임 ID 목록을 기반으로 게임 닉네임을 삭제하는 메서드입니다.
	 * @param ids 삭제할 닉네임의 ID 목록
	 */
	@Override
	public void deleteNickname(List<Long> ids) {
		try {
			// 각 닉네임 ID에 대해 삭제 작업 수행
			for (Long id : ids) {
				nicknameRepository.deleteById(id);
			}
		} catch (Exception e) {
			throw new NicknameException("닉네임 삭제 중 오류가 발생했습니다. :", e);
		}
	}
	

	/**
	 * 같은 게임에서 닉네임 중복 존재 여부를 확인하는 메서드입니다.
	 * 
	 * @param gameName 확인할 게임 이름
	 * @param nickname 확인할 닉네임
	 * @return 중복 여부
	 */
	public boolean checkNicknameExists(String gameName, String nickname) {
		return nicknameRepository.existsByGameNameAndNickname(gameName, nickname);
	}

}
