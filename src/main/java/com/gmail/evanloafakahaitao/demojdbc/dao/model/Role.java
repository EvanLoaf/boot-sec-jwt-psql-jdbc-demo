package com.gmail.evanloafakahaitao.demojdbc.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 4390814386363849151L;

    private Long id;
    private RoleEnum name;
    private List<Permission> permissions = new ArrayList<>();

}
