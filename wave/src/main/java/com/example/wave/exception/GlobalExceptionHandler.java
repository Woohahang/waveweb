package com.example.wave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<String> handleUserServiceException(UserServiceException ex) {
	    log.error("사용자 서비스 예외 발생: {}", ex.getMessage(), ex);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	// 모든 RuntimeException 처리
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		log.error("예외 발생: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에 오류가 발생했습니다.");
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		log.error("알 수 없는 예외 발생: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 오류가 발생했습니다.");
	}
	
	@ExceptionHandler(NicknameException.class)
	public ResponseEntity<String> handleNicknameException(NicknameException ex) {
	    log.error("닉네임 서비스 예외 발생: {}", ex.getMessage(), ex);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

}