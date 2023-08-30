package com.xfour.businesscapitalloan.service.impl;


import com.xfour.businesscapitalloan.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleService = new RoleServiceImpl(roleRepository);
    }

    @Test
    void getOrSave_RoleExists() {

    }

    @Test
    void getOrSave_RoleDoesNotExist() {

    }
}
