package com.gmail.evanloafakahaitao.demojdbc.controllers;

import com.gmail.evanloafakahaitao.demojdbc.dao.model.User;
import com.gmail.evanloafakahaitao.demojdbc.security.JwtProvider;
import com.gmail.evanloafakahaitao.demojdbc.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class UsersController {
    
    private UserService userService;
    private JwtProvider jwtProvider;

    public UsersController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/user/users")
    @PreAuthorize("hasAuthority('user_permission')")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/admin/users/{id}")
    @PreAuthorize("hasAuthority('admin_permission')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id).orElse(new User());
        return ResponseEntity.ok(user);
    }
}
