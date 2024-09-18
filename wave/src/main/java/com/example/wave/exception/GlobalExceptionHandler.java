package com.example.wave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		
		System.out.println("에러 발생");
		
		// RuntimeException에 대한 처리

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("서버에서 오류가 발생했습니다: " + ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효성 검사 오류: " + errorMessage);
	}
	
}