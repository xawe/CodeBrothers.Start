package com.codebrothers.services.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.exceptions.ResourceForbiddenException;

@Service
public class AuthService {
    @Autowired
    TokenService jwtToken;

    @Autowired
    private UserService userService;

    public String findUser(User user) {
        // validando se o usuário está cadastrado no banco
        User u = userService.authenticateUser(user);
        
        if (u == null)
            throw new ResourceForbiddenException("Usuário e/ou senha inválidos!");

        return jwtToken.generateToken(u);
    }
}
