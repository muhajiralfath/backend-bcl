package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.repository.BillRepository;
import com.xfour.businesscapitalloan.service.UmkmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private UmkmService umkmService;

    @InjectMocks
    private BillServiceImpl billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Bill bill = new Bill();
        when(billRepository.save(bill)).thenReturn(bill);

        Bill result = billService.create(bill);

        assertEquals(bill, result);
        verify(billRepository).save(bill);
    }

    @Test
    void testFindById() {
        Bill bill = new Bill();
        String billId = "bill123";
        when(billRepository.findById(billId)).thenReturn(Optional.of(bill));

        Bill result = billService.findById(billId);

        assertEquals(bill, result);
        verify(billRepository).findById(billId);
    }





    @Test
    void testGetAllBillByDebtorIdThrowsNotFound() {
        String debtorId = "debtor123";
        when(umkmService.getByDebtorId(debtorId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        assertThrows(ResponseStatusException.class, () -> billService.getAllBillByDebtorId(debtorId));

        verify(umkmService).getByDebtorId(debtorId);
        verify(billRepository, never()).findAllByUmkmId(any());
    }

}
