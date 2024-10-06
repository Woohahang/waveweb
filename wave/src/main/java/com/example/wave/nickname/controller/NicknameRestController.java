package com.example.wave.nickname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wave.nickname.service.NicknameService;

@RestController
@RequestMapping("/api")
public class NicknameRestController {

	@Autowired
	private NicknameService nicknameService;

//	@GetMapping("/list")
//	public List<Nickname> listNicknames() {
//		List<Nickname> nicknames = nicknameService.getNicknames("282793473462239232");
//
//		return null;
//
//	}

}
