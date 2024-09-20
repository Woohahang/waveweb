package com.example.wave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@GetMapping("/logout/oauth2")
	public String oauth2logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}

	@GetMapping("/account")
	public String userNicknameInfo() {
		
		return "pages/account";
	}
	
	@GetMapping("/addnickname")
	public String addNickname() {
		
		return "";
	}

}
