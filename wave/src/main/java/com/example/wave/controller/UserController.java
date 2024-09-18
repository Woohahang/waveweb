package com.example.wave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.wave.dto.UserDTO;
import com.example.wave.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
    private HttpSession httpSession;
	
	@GetMapping("/logout/oauth2")
	public String oauth2logout(OAuth2User principal) {
		httpSession.invalidate();
		return "redirect:/";
	}
	
}
