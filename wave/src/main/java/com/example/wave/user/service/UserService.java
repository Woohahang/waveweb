package com.example.wave.user.service;

import com.example.wave.user.dto.UserDTO;

public interface UserService {
	void saveOrUpdateUser(UserDTO userDTO);
    void deleteUser(String userId);
}
