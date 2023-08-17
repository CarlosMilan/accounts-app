package com.mm.accounts.users.service;

import com.mm.accounts.users.dto.AppUserDTO;
import com.mm.accounts.users.dto.AppUserResponseDTO;
import com.mm.accounts.users.dto.MessageDTO;
import com.mm.accounts.users.entity.AppUser;
import com.mm.accounts.users.entity.Role;
import com.mm.accounts.users.entity.RoleName;
import com.mm.accounts.users.exception.RoleNotFoundException;
import com.mm.accounts.users.repository.RoleRepository;
import com.mm.accounts.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Override
    public AppUserResponseDTO getUserDetails(String id) {
        AppUser appUser = userRepository.findById(UUID.fromString(id))
                .orElseThrow( () -> new UsernameNotFoundException("User Not Found"));

        return AppUserResponseDTO.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .name(appUser.getName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .build();
    }

    @Override
    public MessageDTO createUser(AppUserDTO userDTO) {
        AppUser user = save(AppUser.builder().build(), userDTO);
        return new MessageDTO("User " + user.getUsername() + " created", Instant.now());
    }

    @Override
    public MessageDTO updateUser(String id, AppUserDTO userDTO) {
        AppUser user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        save(user, userDTO);
        return new MessageDTO("User updated", Instant.now());
    }

    private AppUser save(AppUser user, AppUserDTO userDTO) {
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        Set<Role> roles = new HashSet<>();
        userDTO.getRoles().forEach(r -> {
            Role role = roleRepository.findByRoleName(RoleName.valueOf(r))
                    .orElseThrow( () -> new RoleNotFoundException("Invalid Role name"));
            roles.add(role);
        });
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public MessageDTO deleteUser(String id) {
        userRepository.deleteById(UUID.fromString(id));
        return new MessageDTO("User deleted", Instant.now());
    }

    @Override
    public MessageDTO upgradeRoleUser(String id) {
        return null;
    }
}
