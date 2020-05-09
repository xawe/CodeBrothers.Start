package com.codebrothers.services.auth.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codebrothers.services.auth.entities.User;
import com.codebrothers.services.auth.services.AuthService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api/auth/v1", produces = "application/json;charset=UTF-8;")
public class AuthController {
   @Autowired
   AuthService service;
   
   	@PreAuthorize("permitAll()")
    @GetMapping()
    @ApiOperation(value = "API for authentication", response = Object.class, responseContainer = "List")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().body(service.findUser());
    }        
    
}