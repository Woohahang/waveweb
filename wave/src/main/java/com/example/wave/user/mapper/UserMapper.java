package com.example.wave.user.mapper;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.wave.user.dto.UserDto;
import com.example.wave.user.entity.User;

public class UserMapper {

	public static UserDto toDTO(OAuth2User oauth2User) {
        return UserDto.builder()
                .discordId(oauth2User.getAttribute("id"))
                .userName(oauth2User.getAttribute("username"))
                .globalName(oauth2User.getAttribute("global_name"))
                .build();
	}

	public static User toEntity(UserDto userDTO) {
		return User.builder()
                .userId(userDTO.getDiscordId())
                .username(userDTO.getUserName())
                .globalName(userDTO.getGlobalName())
                .build();
	}

}
