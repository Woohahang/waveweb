package com.example.wave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.wave.dto.UserDTO;
import com.example.wave.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
    private HttpSession httpSession;
	
	@GetMapping("/login/oauth2")
	public String oauth2Login(@AuthenticationPrincipal OAuth2User principal) {

		// OAuth2User에서 필요한 정보를 추출하여 DTO에 매핑
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(principal.getAttribute("id")); // 사용자 ID
		userDTO.setUsername(principal.getAttribute("username")); // 사용자 이름
		userDTO.setGlobalName(principal.getAttribute("global_name")); // 글로벌 이름
		userDTO.setLocale(principal.getAttribute("locale")); // 언어 설정

		// DTO 정보를 사용하여 사용자 정보를 저장 또는 업데이트
		userService.saveOrUpdateUser(userDTO);
		
		// 세션에 사용자 정보 저장
        httpSession.setAttribute("userDTO", userDTO);
		
		log.info("userDTO : " + userDTO);
		return "redirect:/"; // 리다이렉트할 페이지
	}
	
	
	@GetMapping("/logout/oauth2")
	public String oauth2logout(OAuth2User principal) {
		httpSession.invalidate();
		return "redirect:/";
	}
	
}
