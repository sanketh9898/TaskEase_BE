package com.example.taskease.service;

import com.example.taskease.dto.UserDto;
import com.example.taskease.model.User;
import com.example.taskease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(User user) {
        User savedUser = userRepository.save(user);
        return convertEntityToDto(savedUser);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(this::convertEntityToDto);
    }
    @Override
    public UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        // Do NOT include password in DTO
        return userDto;
    }
}