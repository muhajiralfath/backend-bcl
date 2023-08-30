package com.xfour.businesscapitalloan.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

class UserDetailsImplTest {

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(() -> "ROLE_USER");
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email("test@example.com")
                .password("password")
                .authorities(authorities)
                .build();

        assertEquals(authorities, userDetails.getAuthorities());
    }

    @Test
    void getPassword() {
        String password = "password";
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email("test@example.com")
                .password(password)
                .build();

        assertEquals(password, userDetails.getPassword());
    }

    @Test
    void getUsername() {
        String email = "test@example.com";
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email(email)
                .password("password")
                .build();

        assertEquals(email, userDetails.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email("test@example.com")
                .password("password")
                .build();

        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email("test@example.com")
                .password("password")
                .build();

        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email("test@example.com")
                .password("password")
                .build();

        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .userId("123")
                .email("test@example.com")
                .password("password")
                .build();

        assertTrue(userDetails.isEnabled());
    }
}
