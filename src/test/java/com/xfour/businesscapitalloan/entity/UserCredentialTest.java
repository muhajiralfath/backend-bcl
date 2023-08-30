package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserCredentialTest {

    @InjectMocks
    private UserCredential userCredential;

    @Test
    void testUserCredentialEntity() {
        assertNotNull(userCredential, "UserCredential entity should not be null");
    }

    @Test
    void testEmailField() {
        userCredential.setEmail("user@example.com");
        assertEquals("user@example.com", userCredential.getEmail(), "Email field should match the set value");
    }

    @Test
    void testPasswordField() {
        userCredential.setPassword("password");
        assertEquals("password", userCredential.getPassword(), "Password field should match the set value");
    }

    @Test
    void testRolesField() {
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        userCredential.setRoles(roles);
        assertEquals(roles, userCredential.getRoles(), "Roles field should match the set value");
    }

    @Test
    void testProfilePictureField() {
        ProfilePicture profilePicture = new ProfilePicture();
        userCredential.setProfilePicture(profilePicture);
        assertEquals(profilePicture, userCredential.getProfilePicture(), "ProfilePicture field should match the set value");
    }

    @Test
    void testAddRoleMethod() {

    }

    @Test
    void testGetRolesMethod() {
    }

    @Test
    void testIdField() {
        userCredential.setId("123");
        assertNotNull(userCredential.getId(), "Id field should not be null");
    }

    @Test
    void testCreatedAtField() {
    }

    @Test
    void testUpdatedAtField() {
    }

    @Test
    void testToStringMethod() {
    }
}
