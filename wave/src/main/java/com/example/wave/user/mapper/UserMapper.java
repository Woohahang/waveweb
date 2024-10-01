package com.example.wave.user.mapper;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.wave.user.dto.UserDTO;
import com.example.wave.user.entity.User;

public class UserMapper {

	public static UserDTO toDTO(OAuth2User oauth2User) {
        return UserDTO.builder()
                .discordId(oauth2User.getAttribute("id"))
                .userName(oauth2User.getAttribute("username"))
                .globalName(oauth2User.getAttribute("global_name"))
                .build();
	}

	public static User toEntity(UserDTO userDTO) {
		return User.builder()
                .userId(userDTO.getDiscordId())
                .username(userDTO.getUserName())
                .globalName(userDTO.getGlobalName())
                .build();
	}

}
