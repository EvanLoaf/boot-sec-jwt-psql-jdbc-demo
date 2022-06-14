package com.gmail.evanloafakahaitao.demojdbc.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = 3964010146771620150L;

    private Long id;
    private PermissionEnum name;
}
