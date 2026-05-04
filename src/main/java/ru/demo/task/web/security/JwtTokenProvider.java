package ru.demo.task.web.security;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.demo.task.service.UserService;
import ru.demo.task.service.props.JwtProperties;

import java.security.Key;

@Service
@AllArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final UserService userService;
    private final Key key;
    private UserDetailsService userDetailsService;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

}
