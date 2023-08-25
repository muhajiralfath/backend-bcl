package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.constant.ERole;
import com.xfour.businesscapitalloan.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findFirstByRole(ERole role);
}
