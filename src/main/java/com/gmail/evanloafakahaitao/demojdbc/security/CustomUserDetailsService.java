package com.gmail.evanloafakahaitao.demojdbc.security;

import com.gmail.evanloafakahaitao.demojdbc.dao.UserDao;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username).orElse(null);
        UserPrincipal userPrincipal;
        if (user != null) {
            userPrincipal = new UserPrincipal(user);
        } else {
            throw new UsernameNotFoundException("Current User Not Found");
        }
        return userPrincipal;
    }
}
