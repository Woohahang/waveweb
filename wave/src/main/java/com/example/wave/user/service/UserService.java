package com.example.wave.user.service;

import com.example.wave.user.dto.UserDto;

import jakarta.validation.Valid;

public interface UserService {
	void saveOrUpdateUser(@Valid UserDto userDTO);
    void deleteUser(@Valid String userId);
}
