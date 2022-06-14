package com.gmail.evanloafakahaitao.demojdbc.controllers.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RegistrationRequest implements Serializable {

    private static final long serialVersionUID = -530995673606304879L;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
