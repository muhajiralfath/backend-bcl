package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Debtor;
import com.xfour.businesscapitalloan.entity.UserCredential;
import com.xfour.businesscapitalloan.entity.UserDetailsImpl;
import com.xfour.businesscapitalloan.model.response.DebtorResponse;
import com.xfour.businesscapitalloan.repository.DebtorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DebtorServiceImplTest {

    @InjectMocks
    private DebtorServiceImpl debtorService;

    @Mock
    private DebtorRepository debtorRepository;

    private Debtor debtor;
    private UserCredential userCredential;

    @BeforeEach
    void setUp() {
        debtor = Debtor.builder()
                .id("1")
                .nik("1234567890")
                .npwp("1234567890")
                .name("John Doe")
                .handphone("1234567890")
                .birthPlace("City")
                .birthDate(null)
                .gender("Male")
                .status("Single")
                .address("123 Main St")
                .job("Software Developer")
                .userCredential(userCredential)
                .build();

        userCredential = UserCredential.builder()
                .id("user1")
                .email("john@example.com")
                .password("password")
                .build();

        debtor.setUserCredential(userCredential);
    }

    @Test
    void create() {
        when(debtorRepository.save(any(Debtor.class))).thenReturn(debtor);

        Debtor savedDebtor = debtorService.create(debtor);

        assertNotNull(savedDebtor);
        assertEquals(debtor.getId(), savedDebtor.getId());
        assertEquals(debtor.getName(), savedDebtor.getName());

        verify(debtorRepository, times(1)).save(any(Debtor.class));
    }

    @Test
    void get() {
        when(debtorRepository.findById(debtor.getId())).thenReturn(Optional.of(debtor));

        Debtor retrievedDebtor = debtorService.get(debtor.getId());

        assertNotNull(retrievedDebtor);
        assertEquals(debtor.getId(), retrievedDebtor.getId());
        assertEquals(debtor.getName(), retrievedDebtor.getName());

        verify(debtorRepository, times(1)).findById(debtor.getId());
    }

    @Test
    void getByAuthentication() {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(debtorRepository.findFirstByUserCredential_Id(userDetails.getUserId())).thenReturn(Optional.of(debtor));

        DebtorResponse debtorResponse = debtorService.getByAuthentication(authentication);

        assertNotNull(debtorResponse);
        assertEquals(debtor.getId(), debtorResponse.getDebtorId());
        assertEquals(debtor.getName(), debtorResponse.getName());
    }

    @Test
    void getById() {
        when(debtorRepository.findById(debtor.getId())).thenReturn(Optional.of(debtor));

        DebtorResponse debtorResponse = debtorService.getById(debtor.getId());

        assertNotNull(debtorResponse);
        assertEquals(debtor.getId(), debtorResponse.getDebtorId());
        assertEquals(debtor.getName(), debtorResponse.getName());
    }


}
