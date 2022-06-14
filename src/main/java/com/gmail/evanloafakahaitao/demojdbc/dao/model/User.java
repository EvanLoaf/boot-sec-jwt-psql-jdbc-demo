package com.gmail.evanloafakahaitao.demojdbc.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -8868156601648150756L;

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
