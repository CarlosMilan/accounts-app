package com.mm.accounts.users.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AppUserDTO {

    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Set<String> roles;
}
