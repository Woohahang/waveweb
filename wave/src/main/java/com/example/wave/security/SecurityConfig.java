package com.example.wave.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Spring Security의 보안 설정을 정의합니다.
 */
@Configuration // Spring의 Configuration 클래스로 설정
@EnableWebSecurity // Spring Security를 활성화
public class SecurityConfig {

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


	/**
	 * CORS 설정을 구성하는 메서드입니다.
	 * 
	 * @return CORS 설정을 포함하는 CorsConfigurationSource 객체
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.setAllowedOrigins(List.of("http://localhost:3000"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setExposedHeaders(List.of("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}


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
					.requestMatchers("/","/favicon.ico","/logo192.png" , "/manifest.json" ,"/index.html", "/static/**", "/oauth/**", "/login/**").permitAll() // 모든 사용자에게 접근 허용
					.anyRequest().authenticated() // 그 외의 모든 요청은 인증을 요구
					)

			// OAuth2 로그인을 위한 설정
			.oauth2Login(login -> login
					.loginPage("/dddd") // 사용자 정의 로그인 페이지
					.successHandler(customAuthenticationSuccessHandler) // 인증 성공 시 호출할 핸들러 설정
					.authorizationEndpoint(authorization -> authorization
		                    .baseUri("/oauth2/authorize") // OAuth2 인증 엔드포인트
		                )
					)

			// CORS 설정
			.cors(cors -> cors
					.configurationSource(corsConfigurationSource()))

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