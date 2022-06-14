package com.gmail.evanloafakahaitao.demojdbc.services.impl;

import com.gmail.evanloafakahaitao.demojdbc.dao.RoleDao;
import com.gmail.evanloafakahaitao.demojdbc.dao.UserDao;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.Role;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.RoleEnum;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.User;
import com.gmail.evanloafakahaitao.demojdbc.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {

    @Value("${user.role.default}")
    private String DEFAULT_ROLE;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int save(User user) {
        log.info("Service - Saving User : {}", user);
        user.setRole(
                Role.builder().name(RoleEnum.getRole(DEFAULT_ROLE)).build()
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        log.info("Service - Searching for User with id : {}", id);
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        log.info("Service - Searching for User with email : {}", email);
        return userDao.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user with email : " + email));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.info("Service - Searching for ALL Users");
        return userDao.findAll();
    }
}
