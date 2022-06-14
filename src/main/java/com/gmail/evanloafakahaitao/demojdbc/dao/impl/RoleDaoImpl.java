package com.gmail.evanloafakahaitao.demojdbc.dao.impl;

import com.gmail.evanloafakahaitao.demojdbc.dao.RoleDao;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.Permission;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.PermissionEnum;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.Role;
import com.gmail.evanloafakahaitao.demojdbc.dao.model.RoleEnum;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Log4j2
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Role> findByName(String name) {
        log.info("Searching for Role with name : {}", name);
        String psql = "SElECT id, name FROM role AS r WHERE r.name = ?";
        return jdbcTemplate.queryForObject(
                psql,
                ((rs, rowNum) -> Optional.of(new Role(
                        rs.getLong("id"),
                        RoleEnum.getRole(rs.getString("name")),
                        jdbcTemplate.query(
                                "SELECT p.id, p.name FROM permission AS p INNER JOIN \"authorization\" a ON p.id = a.permission_id WHERE a.role_id = ?",
                                ((rsPerm, rowNumPerm) -> new Permission(
                                        rsPerm.getLong("id"),
                                        PermissionEnum.getPermission(rsPerm.getString("name"))
                                )),
                                rs.getLong("id")
                        )
                ))),
                name
        );
    }
}
