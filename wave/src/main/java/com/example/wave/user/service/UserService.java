package com.example.wave.user.service;

import com.example.wave.user.dto.UserDTO;

import jakarta.validation.Valid;

public interface UserService {
	void saveOrUpdateUser(@Valid UserDTO userDTO);
    void deleteUser(@Valid String userId);
}
