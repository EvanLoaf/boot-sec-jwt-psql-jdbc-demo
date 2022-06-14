package com.gmail.evanloafakahaitao.demojdbc.controllers.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {
    
    private static final long serialVersionUID = 836950558509138829L;
    
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
