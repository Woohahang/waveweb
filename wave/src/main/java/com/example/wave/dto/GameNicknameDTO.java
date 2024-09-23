package com.example.wave.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Builder // 빌더 패턴을 위한 어노테이션
public class GameNicknameDTO {
	
	private String userDiscordId;
	private String gameName; // 선택한 게임의 ID
	private String nickname; // 사용자 입력

}
