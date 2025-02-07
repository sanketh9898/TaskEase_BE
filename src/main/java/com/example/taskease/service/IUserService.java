package com.example.taskease.service;

import com.example.taskease.dto.UserDto;
import com.example.taskease.model.User;

import java.util.Optional;
public interface IUserService {
    UserDto createUser(User user);
    Optional<UserDto> findById(Long id);

    UserDto convertEntityToDto(User user);
}