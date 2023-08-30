package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminTest {

    @InjectMocks
    private Admin admin;

    @Mock
    private UserCredential userCredential;

    @Test
    void testAdminEntity() {
        assertNotNull(admin, "Admin entity should not be null");
    }

    @Test
    void testNameField() {
        admin.setName("John Doe");
        String name = admin.getName();
        assertNotNull(name, "Name should not be null");
    }


    @Test
    void testToStringMethod() {
        assertNotNull(admin.toString(), "toString method should not return null");
    }
}
