package com.gmail.evanloafakahaitao.demojdbc.dao.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum PermissionEnum {

    ADMIN_PERMISSION,
    USER_PERMISSION;

    public static PermissionEnum getPermission(String permission) {
        try {
            return PermissionEnum.valueOf(permission.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Permission {} not found", permission.toUpperCase(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
