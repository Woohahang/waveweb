package com.example.wave.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(RuntimeException.class) // 모든 예외를 처리
	public String handleRuntimeException(RuntimeException ex) {
		log.info("예외 발생");
		log.error("예외 발생: {}", ex.getMessage(), ex); // 에러 메시지와 스택 트레이스 기록
		
		
		
		
		
		return "error";
	}
	
}