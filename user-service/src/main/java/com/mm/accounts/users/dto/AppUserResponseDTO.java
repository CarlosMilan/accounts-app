package com.mm.accounts.users.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AppUserResponseDTO {

    private UUID id;
    private String username;
    private String name;
    private String lastName;
    private String email;

}
