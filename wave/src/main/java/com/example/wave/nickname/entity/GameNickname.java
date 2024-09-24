package com.example.wave.nickname.entity;

import com.example.wave.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@Table(name = "game_nicknames")
@NoArgsConstructor
@AllArgsConstructor
public class GameNickname {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성 전략
	private Long id;

	@ManyToOne // User와의 관계 설정
	@JoinColumn(name = "user_discord_id", referencedColumnName = "user_discord_id", nullable = false) // 외래 키 컬럼 이름
	private User user;

	@Column(name = "game_name", nullable = false, length = 50)
	private String gameName;
	
	@Column(nullable = false, length = 50)
	private String nickname; // 사용자 입력

}

// @JoinColumn 의 name 속성은 game_nicknames 테이블의 외래 키 컬럼 이름을 지정합니다.
// @JoinColumn 의 referencedColumnName 는 User 테이블에서 참조할 컬럼 이름을 지정합니다.