package com.example.wave.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class PageController {
	
	@GetMapping("/api/test")
    public ResponseEntity<String> test() {
        log.info("test 요청 성공");
        return ResponseEntity.ok("테스트 요청이 성공했습니다."); // 응답 메시지와 함께 200 OK 상태 반환
    }
	
	@GetMapping("/dddd")
	public ResponseEntity<String> dsadsadsadsa() {
		log.info("dsadasdsadadasdsadasdas  여기서 디스코드로 리디렉션하는 처리를 따로 해야되는구나?");
		 return ResponseEntity.ok("로그인 하세요.");
	}
}