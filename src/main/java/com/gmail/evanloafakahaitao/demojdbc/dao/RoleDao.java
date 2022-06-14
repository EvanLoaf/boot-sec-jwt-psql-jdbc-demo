package com.gmail.evanloafakahaitao.demojdbc.dao;

import com.gmail.evanloafakahaitao.demojdbc.dao.model.Role;

import java.util.Optional;

public interface RoleDao {

    Optional<Role> findByName(String name);
}
