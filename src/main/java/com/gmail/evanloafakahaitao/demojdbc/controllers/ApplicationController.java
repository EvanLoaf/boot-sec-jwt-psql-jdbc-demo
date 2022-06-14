package com.gmail.evanloafakahaitao.demojdbc.controllers;

import com.gmail.evanloafakahaitao.demojdbc.controllers.model.AuthRequest;
import com.gmail.evanloafakahaitao.demojdbc.controllers.model.RegistrationRequest;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.User;
import com.gmail.evanloafakahaitao.demojdbc.security.JwtProvider;
import com.gmail.evanloafakahaitao.demojdbc.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class ApplicationController {
    
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;

    public ApplicationController(UserService userService, JwtProvider jwtProvider, ModelMapper modelMapper) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.modelMapper = modelMapper;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        log.info("AppController - Signup");
        User user = convertToUser(registrationRequest);
        userService.save(user);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> authorize(@RequestBody @Valid AuthRequest authRequest) {
        User user = userService.findByEmailAndPassword(
                authRequest.getEmail(), 
                authRequest.getPassword()
        );
        String token = jwtProvider.generateToken(user.getEmail());
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }
    
    private User convertToUser(RegistrationRequest registrationRequest) {
        return modelMapper.map(registrationRequest, User.class);
    }
    
}
