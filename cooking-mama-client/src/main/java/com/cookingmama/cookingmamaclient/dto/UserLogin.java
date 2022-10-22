package com.cookingmama.cookingmamaclient.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserLogin {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String accessToken;

}
