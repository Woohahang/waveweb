package com.example.wave.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Builder // 빌더 패턴을 위한 어노테이션
public class GameNicknameDTO {

	@NotBlank(message = "User Discord ID는 필수입니다.")
	private String userDiscordId;

	@NotBlank(message = "게임 이름은 필수입니다.")
	@Size(max = 50, message = "게임 이름은 50자 이내여야 합니다.")
	private String gameName; // 선택한 게임의 ID

	@NotBlank(message = "닉네임은 필수입니다.")
	@Size(max = 50, message = "닉네임은 50자 이내여야 합니다.")
	private String nickname; // 사용자 입력

}
