package com.example.wave.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.wave.dto.GameNicknameDTO;
import com.example.wave.dto.UserDTO;
import com.example.wave.entity.GameNickname;
import com.example.wave.enums.Games;
import com.example.wave.service.GameService;
import com.example.wave.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	private GameService gameService;

	@Autowired
	private UserService userService;
	
	/**
     * OAuth2 로그아웃 처리 메서드입니다.
     * @param httpSession 현재 세션
     * @return 메인 페이지로 리다이렉트
     */
	@GetMapping("/logout/oauth2")
	public String logoutFromOAuth2(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/";
	}
	
	
	@PostMapping("/deleteUser")
	public String deleteUser(HttpSession session) {
	    String userId = getCurrentUserId(session); // 현재 사용자 ID 조회
	    userService.deleteUser(userId); // 사용자 삭제 서비스 호출

	    session.invalidate(); // 세션 무효화
	    return "redirect:/"; // 메인 페이지로 리다이렉트
	}

	
	/**
     * 닉네임 추가 폼을 표시하는 메서드입니다.
     * @param model 뷰에 전달할 모델 객체
     * @return 닉네임 추가 페이지
     */
	@GetMapping("/account")
	public String displayAddNicknameForm(Model model) {
		
		model.addAttribute("gameNames", Arrays.asList(Games.values())); // 게임 이름 목록 추가
		model.addAttribute("userGameNicknameDTO", new GameNicknameDTO()); // DTO 추가

		return "pages/account";
	}

	
	/**
     * 닉네임 추가 요청을 처리하는 메서드입니다.
     * @param gameNicknameDTO 사용자 입력을 담고 있는 DTO
     * @param session 현재 세션
     * @return 메인 페이지로 리다이렉트
     */
	@PostMapping("/addnickname")
	public String submitNickname(@ModelAttribute GameNicknameDTO gameNicknameDTO, HttpSession session) {
		log.info("addNickname 메서드 동작");

		String userId = getCurrentUserId(session); // 현재 사용자 ID 조회
		gameNicknameDTO.setUserDiscordId(userId); // DTO에 사용자 ID 설정

		gameService.saveUserGameNickname(gameNicknameDTO);

		return "redirect:/";
	}
	
	
	/**
     * 사용자 닉네임 목록을 조회하는 메서드입니다.
     * @param model 뷰에 전달할 모델 객체
     * @param session 현재 세션
     * @return 닉네임 리스트 페이지
     */
	@GetMapping("/nicknamelist")
	public String nicknamelist(Model model ,HttpSession session) {
		String userId = getCurrentUserId(session); // 현재 사용자 ID 조회
		
		List<GameNickname> gameNicknames  = gameService.getGameNicknames(userId); // 사용자 닉네임 조회
		model.addAttribute("gameNicknames", gameNicknames); // 모델에 닉네임 목록 추가
		
		for (GameNickname gameNickname : gameNicknames) {
	        log.info("게임 이름: {}, 닉네임: {}", gameNickname.getGameName(), gameNickname.getNickname());
	    }
		
		return "pages/nicknamelist";
	}

	
	/**
     * 선택된 닉네임을 삭제하는 메서드입니다.
     * @param nicknameIds 삭제할 닉네임 ID 목록
     * @return 메인 페이지로 리다이렉트
     */
	@PostMapping("/nicknames/delete")
	public String deleteSelectedNicknames(@RequestParam("nicknameIds") List<Long> nicknameIds) {
		gameService.deleteGameNickname(nicknameIds); // 선택된 닉네임 삭제 서비스 호출

		return "redirect:/";
	}


	/**
     * 현재 세션에서 사용자 ID를 조회하는 메서드입니다.
     * @param session 현재 세션
     * @return 사용자 ID
     */
	protected String getCurrentUserId(HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("userDTO"); // 세션에서 사용자 DTO 조회
		return (userDTO != null) ? userDTO.getDiscordId() : null;
	}

}
