package com.example.wave.mapper;

import com.example.wave.dto.GameNicknameDTO;
import com.example.wave.entity.GameNickname;

public interface NicknameMapper {

	GameNickname toEntity(GameNicknameDTO dto);

}