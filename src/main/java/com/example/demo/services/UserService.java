package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.payloads.UserDto;

import java.util.List;

public interface UserService {
 UserDto createUser(UserDto user);
 UserDto updateUser(UserDto user);
 UserDto getUserById(Integer userId);
 List<UserDto> getAllUsers();
 void deleteUser(Integer userId);
}
