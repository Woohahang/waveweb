package com.example.wave.nickname.service;

import java.util.List;

import com.example.wave.nickname.dto.NicknameDto;
import com.example.wave.nickname.entity.Nickname;

import jakarta.validation.Valid;

public interface NicknameService {

	void saveNickname(@Valid NicknameDto dto);
	List<Nickname> getNicknames(String userId);
	void deleteNickname(List<Long> ids);
	
}
