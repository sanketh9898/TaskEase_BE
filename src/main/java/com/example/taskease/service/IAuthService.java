package com.example.taskease.service;

import com.example.taskease.dto.LoginRequest;
import com.example.taskease.dto.RegisterRequest;

public interface IAuthService {
    String register(RegisterRequest request);
    String login(LoginRequest request);
}