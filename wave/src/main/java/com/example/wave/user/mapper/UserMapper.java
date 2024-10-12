package com.example.wave.user.mapper;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.wave.user.dto.UserDto;
import com.example.wave.user.entity.User;

public class UserMapper {

	public static UserDto toDto(OAuth2User oauth2User) {
		String userId = oauth2User.getAttribute("id");
	    String avatarId = oauth2User.getAttribute("avatar");
	    String username = oauth2User.getAttribute("username");
	    String globalName = oauth2User.getAttribute("global_name");

	    // 프로필 이미지 URL 생성
	    String profileImageUrl = String.format("https://cdn.discordapp.com/avatars/%s/%s.png?size=128", userId, avatarId);
	    
        return UserDto.builder()
                .discordId(userId)
                .userName(username)
                .globalName(globalName)
                .profileImageUrl(profileImageUrl)
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
