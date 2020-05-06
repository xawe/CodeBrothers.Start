package com.codebrothers.services.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebrothers.services.auth.services.AuthService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/auth/v1", produces = "application/json;charset=UTF-8;")
public class Auth {
   @Autowired
   AuthService service;
    
    @GetMapping()
    @ApiOperation(value = "API for authentication", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().body(service.findUser());
    }
}