package ru.demo.task.service.impl;

import org.springframework.stereotype.Service;
import ru.demo.task.service.AuthService;
import ru.demo.task.web.dto.auth.JwtRequest;
import ru.demo.task.web.dto.auth.JwtResponse;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
