package com.example.wave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class PageController {

	@GetMapping("/account")
	public String account() {
		log.info("account 페이지 요청");
		return "/pages/account";
	}
	
	@GetMapping("/test")
	public String test() {
		log.info("test 페이지 요청");
		return "/pages/test";
	}
	
}