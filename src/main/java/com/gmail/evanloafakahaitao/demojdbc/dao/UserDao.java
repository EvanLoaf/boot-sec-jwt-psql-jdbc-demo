package com.gmail.evanloafakahaitao.demojdbc.dao;

import com.gmail.evanloafakahaitao.demojdbc.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    int save(User user);

    int deleteById(Long id);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
