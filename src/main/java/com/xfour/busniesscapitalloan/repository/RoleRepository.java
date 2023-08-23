package com.xfour.busniesscapitalloan.repository;

import com.xfour.busniesscapitalloan.constant.ERole;
import com.xfour.busniesscapitalloan.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findFirstByRole(ERole role);
}
