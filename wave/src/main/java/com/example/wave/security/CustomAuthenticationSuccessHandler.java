package com.example.wave.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.wave.dto.UserDTO;
import com.example.wave.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * 인증 성공 후 처리하는 핸들러 클래스입니다.
 * OAuth2 로그인이 성공했을 때 사용자 정보를 세션에 저장하고, 
 * 필요한 경우 사용자 정보 업데이트를 수행합니다.
 */
@Log4j2
@Component // Spring의 빈으로 등록
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	/**
     * 인증 성공 시 호출되는 메서드입니다.
     * 
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @param authentication 인증 정보
     * @throws IOException 입출력 예외
     * @throws ServletException 서블릿 예외
     */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("인증 성공");

		// 인증 정보가 OAuth2User인 경우
		if (authentication.getPrincipal() instanceof OAuth2User) {
			OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal(); // OAuth2User 객체 가져오기
			
			UserDTO userDTO = new UserDTO(); // 사용자 정보를 담을 DTO 생성

			// 사용자 정보 설정
			userDTO.setUserId(oauth2User.getAttribute("id")); // 사용자 ID
			userDTO.setUsername(oauth2User.getAttribute("username")); // 사용자 이름
			userDTO.setGlobalName(oauth2User.getAttribute("global_name")); // 글로벌 이름
			userDTO.setLocale(oauth2User.getAttribute("locale")); // 언어 설정

			log.info("userDTO : " + userDTO);
			
			// 사용자 정보를 저장 또는 업데이트
			userService.saveOrUpdateUser(userDTO);

			// 세션에 UserDTO 저장
	        request.getSession().setAttribute("userDTO", userDTO);
		}

		// 홈으로 리디렉션
		response.sendRedirect("/");
	}

}
