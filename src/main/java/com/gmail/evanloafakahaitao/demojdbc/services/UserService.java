package com.gmail.evanloafakahaitao.demojdbc.services;

import com.gmail.evanloafakahaitao.demojdbc.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    int save(User user);

    int deleteById(Long id);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findAll();
}
