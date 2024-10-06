//package com.example.wave.user.controller;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.wave.user.dto.UserDto;
//import com.example.wave.user.mapper.UserMapper;
//
//@RestController
//@RequestMapping("/api")
//public class UserRestController {
//
//	@GetMapping("/user")
//	public Object getCurrentUser(@AuthenticationPrincipal OAuth2User oauth2User) {
//		if (oauth2User == null) {
//			// 인증되지 않은 경우 홈 페이지로 리디렉션
//			return new ModelAndView("redirect:/");
//		}
//
//		UserDto userDto = UserMapper.toDto(oauth2User);
//		return userDto; // OAuth2User에서 UserDto로 변환하여 반환
//	}
//
//}
