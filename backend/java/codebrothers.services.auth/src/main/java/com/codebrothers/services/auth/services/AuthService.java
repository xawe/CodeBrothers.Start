package com.codebrothers.services.auth.services;

import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;

@Service
public class AuthService {

    public User findUser() {
        return new User();
    }
}
