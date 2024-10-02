package com.example.wave.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.wave.user.dto.UserDto;
import com.example.wave.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
     * OAuth2 로그아웃 처리 메서드입니다.
     * @param httpSession 현재 세션
     * @return 메인 페이지로 리다이렉트
     */
	@GetMapping("/logout/oauth2")
	public String logoutFromOAuth2(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/";
	}
	
	
	/**
     * 사용자 삭제 요청 처리 메서드입니다.
     * @param session 현재 세션
     * @return 메인 페이지로 리다이렉트
     */
	@PostMapping("/deleteUser")
	public String deleteUser(HttpSession session) {
	    String userId = getCurrentUserId(session); // 현재 사용자 ID 조회
	    userService.deleteUser(userId); // 사용자 삭제 서비스 호출

	    session.invalidate(); // 세션 무효화
	    return "redirect:/"; // 메인 페이지로 리다이렉트
	}

	
	/**
     * 현재 세션에서 사용자 ID를 조회하는 메서드입니다.
     * @param session 현재 세션
     * @return 사용자 ID
     */
	protected String getCurrentUserId(HttpSession session) {
		UserDto userDTO = (UserDto) session.getAttribute("userDto"); // 세션에서 사용자 DTO 조회
		return (userDTO != null) ? userDTO.getDiscordId() : null;
	}

}
