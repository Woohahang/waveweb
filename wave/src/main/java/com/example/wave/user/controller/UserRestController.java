package com.example.wave.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.wave.user.dto.UserDto;
import com.example.wave.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@GetMapping("/user/status")
	public ResponseEntity<UserDto> getUserStatus(HttpServletRequest request) {
		System.out.println("getUserStatues 작동");
		
		
	    UserDto userDto = (UserDto) request.getSession().getAttribute("userDto");
	    if (userDto != null) {
	    	System.out.println("반환 성공");
	        return ResponseEntity.ok(userDto); // UserDto 반환
	    } else {
	    	System.out.println("반환 실패");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 인증되지 않은 경우
	    }
	}

}
