package com.mm.accounts.users.service;

import com.mm.accounts.users.dto.AppUserDTO;
import com.mm.accounts.users.dto.AppUserResponseDTO;
import com.mm.accounts.users.dto.MessageDTO;

import java.util.UUID;

public interface UserService {

    AppUserResponseDTO getUserDetails(String id);
    MessageDTO createUser(AppUserDTO userDTO);
    MessageDTO updateUser(String id, AppUserDTO userDTO);
    MessageDTO deleteUser(String id);
    MessageDTO upgradeRoleUser(String id);

}
