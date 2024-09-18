package com.example.wave.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException ex) {
		log.info("예외 발생");
		
		// RuntimeException에 대한 처리
		
		return "error";
	}
	
}