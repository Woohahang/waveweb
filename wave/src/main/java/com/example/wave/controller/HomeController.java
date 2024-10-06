package com.example.wave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("메인 페이지로 요청이 들어왔습니다.");
		return "index.html";
	}

}