package com.gmail.evanloafakahaitao.demojdbc.dao.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum RoleEnum {

    ADMIN,
    USER;

    public static RoleEnum getRole(String role) {
        try {
            return RoleEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Role {} not found", role.toUpperCase(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
