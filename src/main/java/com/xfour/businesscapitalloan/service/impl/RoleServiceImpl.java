package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.constant.ERole;
import com.xfour.businesscapitalloan.entity.Role;
import com.xfour.businesscapitalloan.repository.RoleRepository;
import com.xfour.businesscapitalloan.service.RoleService;
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
