package com.example.wave.user.entity;

import java.util.List;

import com.example.wave.nickname.entity.GameNickname;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_discord_id", length = 20, nullable = false, unique = true)
	private String userId;

	@Column(name = "user_discord_name", nullable = false)
	private String username;

	@Column(name = "user_discord_global_name", nullable = true)
	private String globalName;
	
	@OneToMany(mappedBy = "user")
    private List<GameNickname> nicknames;
	
	@Builder
	public User(String userId, String username, String globalName) {
		this.userId = userId;
		this.username = username;
		this.globalName = globalName;
	}

}