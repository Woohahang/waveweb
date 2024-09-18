package com.example.wave.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.wave.dto.UserDTO;
import com.example.wave.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("인증 성공");

		if (authentication.getPrincipal() instanceof OAuth2User) {
			OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
			UserDTO userDTO = new UserDTO();

			// 사용자 정보 설정
			userDTO.setUserId(oauth2User.getAttribute("id")); // 사용자 ID
			userDTO.setUsername(oauth2User.getAttribute("username")); // 사용자 이름
			userDTO.setGlobalName(oauth2User.getAttribute("global_name")); // 글로벌 이름
			userDTO.setLocale(oauth2User.getAttribute("locale")); // 언어 설정

			log.info("userDTO : " + userDTO);
			
			// 사용자 정보를 저장 또는 업데이트
			userService.saveOrUpdateUser(userDTO);

			// 세션에 UserDTO 저장
	        request.getSession().setAttribute("userDTO", userDTO);
		}

		// home 으로 리디렉션
		response.sendRedirect("/");
	}

}
