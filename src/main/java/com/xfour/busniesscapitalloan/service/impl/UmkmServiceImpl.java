package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.entity.Umkm;
import com.xfour.busniesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.busniesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.busniesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.busniesscapitalloan.model.response.UmkmResponse;
import com.xfour.busniesscapitalloan.repository.UmkmRepository;
import com.xfour.busniesscapitalloan.service.RoleService;
import com.xfour.busniesscapitalloan.service.UmkmService;
import com.xfour.busniesscapitalloan.service.UserService;
import com.xfour.busniesscapitalloan.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UmkmServiceImpl implements UmkmService {
    private final UmkmRepository umkmRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final ValidationUtil validationUtil;
    @Override
    public Umkm findById(String id) {
        return null;
    }

    @Override
    public UmkmResponse create(NewUmkmRequest request) {
        return null;
    }

    @Override
    public UmkmResponse getById(String id) {
        return null;
    }

    @Override
    public UmkmResponse getByDebtorId(String debtorId) {
        return null;
    }

    @Override
    public UmkmResponse getByAuthentication(Authentication authentication) {
        return null;
    }

    @Override
    public Page<UmkmResponse> getAll(SearchUmkmRequest request) {
        return null;
    }

    @Override
    public UmkmResponse update(UpdateUmkmRequest request) {
        return null;
    }
}
