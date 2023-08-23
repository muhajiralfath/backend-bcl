package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.entity.Admin;
import com.xfour.busniesscapitalloan.repository.AdminRepository;
import com.xfour.busniesscapitalloan.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    @Override
    public Admin create(Admin admin) {
        log.info("start create admin");
        Admin save = adminRepository.save(admin);
        log.info("end create admin");
        return save;
    }
}
