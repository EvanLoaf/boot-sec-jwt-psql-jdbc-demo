package com.gmail.evanloafakahaitao.demojdbc.dao.impl;

import com.gmail.evanloafakahaitao.demojdbc.dao.UserDao;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int save(User user) {
        log.info("Saving User : {}", user);
        String psql = "INSERT INTO \"user\" (email, password, first_name, last_name, role_id) VALUES(?, ?, ?, ?, (SELECT r.id FROM role AS r WHERE r.name = ?))";
        return jdbcTemplate.update(
                psql,
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().getName().toString()
        );
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("Searching for User with id : {}", id);
        String psql = "SELECT u.id, u.email, u.password, u.first_name, u.last_name, r.id AS roleid, r.name AS rolename " +
                "FROM \"user\" AS u " +
                "INNER JOIN role AS r ON u.role_id = r.id " +
                "WHERE u.id = ?";
        return jdbcTemplate.queryForObject(
                psql,
                ((rs, rowNum) -> Optional.of(new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        new Role(
                                rs.getLong("roleid"),
                                RoleEnum.getRole(rs.getString("rolename")),
                                jdbcTemplate.query(
                                        "SELECT p.id, p.name FROM permission AS p INNER JOIN \"authorization\" a ON p.id = a.permission_id WHERE a.role_id = ?",
                                        ((rsPerm, rowNumPerm) -> new Permission(
                                                rsPerm.getLong("id"),
                                                PermissionEnum.getPermission(rsPerm.getString("name"))
                                        )),
                                        rs.getLong("roleid")
                                )
                        )
                ))),
                id
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Searching for User with email : {}", email);
        String psql = "SELECT u.id, u.email, u.password, u.first_name, u.last_name, r.id AS roleid, r.name AS rolename " +
                "FROM \"user\" AS u " +
                "INNER JOIN role AS r ON u.role_id = r.id " +
                "WHERE u.email = ?";
        return jdbcTemplate.queryForObject(
                psql,
                ((rs, rowNum) -> Optional.of(new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        new Role(
                                rs.getLong("roleid"),
                                RoleEnum.getRole(rs.getString("rolename")),
                                jdbcTemplate.query(
                                        "SELECT p.id, p.name FROM permission AS p INNER JOIN \"authorization\" a ON p.id = a.permission_id WHERE a.role_id = ?",
                                        ((rsPerm, rowNumPerm) -> new Permission(
                                                rsPerm.getLong("id"),
                                                PermissionEnum.getPermission(rsPerm.getString("name"))
                                        )),
                                        rs.getLong("roleid")
                                )
                        )
                ))),
                email
        );
    }

    @Override
    public List<User> findAll() {
        log.info("Searching for ALL Users");
        String psql = "SELECT u.id, u.email, u.password, u.first_name, u.last_name, r.id AS roleid, r.name AS rolename " +
                "FROM \"user\" AS u " +
                "INNER JOIN role AS r ON u.role_id = r.id " +
                "WHERE u.email = ?";
        return jdbcTemplate.query(
                psql,
                ((rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        new Role(
                                rs.getLong("roleid"),
                                RoleEnum.getRole(rs.getString("rolename")),
                                jdbcTemplate.query(
                                        "SELECT p.id, p.name FROM permission AS p INNER JOIN \"authorization\" a ON p.id = a.permission_id WHERE a.role_id = ?",
                                        ((rsPerm, rowNumPerm) -> new Permission(
                                                rsPerm.getLong("id"),
                                                PermissionEnum.getPermission(rsPerm.getString("name"))
                                        )),
                                        rs.getLong("roleid")
                                )
                        )
                ))
        );
    }
}
