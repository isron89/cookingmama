package com.cookingmama.cookingmamaclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    private String username;
    private String password;

    public LoginForm() {
    }

    public LoginForm(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

}
