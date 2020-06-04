package com.codebrothers.services.auth.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.services.AuthService;
import com.codebrothers.services.auth.services.TokenService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/auth/v1", produces = "application/json;charset=UTF-8;")
public class AuthController {
    @Autowired
    AuthService service;

    @Autowired
    TokenService jwtToken;

    @PostMapping("/login")
    @ApiOperation(value = "API for authentication", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> login(@RequestBody @Valid User user) {
        return ResponseEntity.ok().body(service.findUser(user));
    }

    @PostMapping("/validate")
    @ApiOperation(value = "Validatate Token", response = Object.class)
    public ResponseEntity<?> validate(@RequestHeader String Authorization) {
        return ResponseEntity.ok().body(jwtToken.validateToken(Authorization));
    }
}