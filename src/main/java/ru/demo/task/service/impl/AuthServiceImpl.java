package ru.demo.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.demo.task.domain.exeption.AccessDediedException;
import ru.demo.task.domain.user.User;
import ru.demo.task.service.AuthService;
import ru.demo.task.service.UserService;
import ru.demo.task.web.dto.auth.JwtRequest;
import ru.demo.task.web.dto.auth.JwtResponse;
import ru.demo.task.web.security.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        try {
            return jwtTokenProvider.refreshUserTokens(refreshToken);
        } catch (AccessDediedException e) {
            throw new RuntimeException(e);
        }
    }
}
