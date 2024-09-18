  package com.example.wave.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security의 보안 설정을 정의합니다.
 */
@Configuration // Spring의 Configuration 클래스로 설정
@EnableWebSecurity // Spring Security를 활성화
public class SecurityConfig {

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	/**
     * SecurityFilterChain을 구성하여 HTTP 보안 설정을 정의합니다.
     *
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain
     * @throws Exception 설정 중 발생할 수 있는 예외
     */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			// HTTP 요청에 대한 권한을 설정
			.authorizeHttpRequests(authz -> authz
					.requestMatchers("/", "/oauth/**").permitAll() // 모든 사용자에게 접근 허용
					.anyRequest().authenticated() // 그 외의 모든 요청은 인증을 요구
					)

			// OAuth2 로그인을 위한 설정
			.oauth2Login(login -> login
					.loginPage("/") // 사용자 정의 로그인 페이지
					.successHandler(customAuthenticationSuccessHandler) // 인증 성공 시 호출할 핸들러 설정
					)

			// 로그아웃을 위한 설정
			.logout(logout -> logout
					.logoutUrl("/logout") // 기본 로그아웃 URL
					.logoutSuccessUrl("/") // 로그아웃 후 리디렉션할 URL
					.invalidateHttpSession(true) // 세션 무효화
					.clearAuthentication(true) // 인증 정보 삭제
					.deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
					.permitAll()
					);
	
		return http.build();
	}

}