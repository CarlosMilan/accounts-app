package com.mm.accounts.users.repository;

import com.mm.accounts.users.entity.Role;
import com.mm.accounts.users.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(RoleName roleName);
}
