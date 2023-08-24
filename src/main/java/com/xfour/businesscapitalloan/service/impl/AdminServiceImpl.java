package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Admin;
import com.xfour.businesscapitalloan.repository.AdminRepository;
import com.xfour.businesscapitalloan.service.AdminService;
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

    @Override
    public Boolean isAdminEmpty() {
        return adminRepository.count() == 0;
    }
}
