package com.codebrothers.services.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;

@Service
public class AuthService {
    @Autowired
    TokenService  jwtToken;

    public String findUser(User user) {
        return jwtToken.generateToken(user);
    }
}
