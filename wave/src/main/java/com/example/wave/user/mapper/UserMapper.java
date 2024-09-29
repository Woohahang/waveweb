package com.example.wave.user.mapper;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.wave.user.dto.UserDTO;

public class UserMapper {

	public static UserDTO toUserDTO(OAuth2User oauth2User) {
        return UserDTO.builder()
                .discordId(oauth2User.getAttribute("id"))
                .userName(oauth2User.getAttribute("username"))
                .globalName(oauth2User.getAttribute("global_name"))
                .build();
    }
	
}
