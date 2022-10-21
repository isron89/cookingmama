package com.cookingmama.cookingmamaclient.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class User {
    private String email;
    private String username;
    private String password;
    private String role;

    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role="user";
    }
}
