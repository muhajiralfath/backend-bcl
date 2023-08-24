package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.UserCredential;
import com.xfour.businesscapitalloan.repository.UserCredentialRepository;
import com.xfour.businesscapitalloan.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCredentialServiceImpl implements UserCredentialService {
    private final UserCredentialRepository userCredentialRepository;
    @Override
    public Boolean isAdminEmpty(String email) {
        Optional<UserCredential> firstByEmail = userCredentialRepository.findFirstByEmail(email);

        if (firstByEmail.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
}
