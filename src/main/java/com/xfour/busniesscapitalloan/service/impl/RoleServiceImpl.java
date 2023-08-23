package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.constant.ERole;
import com.xfour.busniesscapitalloan.entity.Role;
import com.xfour.busniesscapitalloan.repository.RoleRepository;
import com.xfour.busniesscapitalloan.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private  final RoleRepository roleRepository;
    @Override
    public Role getOrSave(String roleNumber) {
        log.info("start getOrSave");
        ERole eRole = ERole.getByIndex(roleNumber);
        Role role = roleRepository.findFirstByRole(eRole)
                .orElseGet(() -> roleRepository.save(Role.builder().role(eRole).build()));
        log.info("start getOrSave");
        return role;
    }
}
