package ru.demo.task.service;

import ru.demo.task.web.dto.auth.JwtRequest;
import ru.demo.task.web.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
