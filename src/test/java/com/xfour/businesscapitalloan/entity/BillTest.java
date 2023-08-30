package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BillTest {

    @InjectMocks
    private Bill bill;

    @Mock
    private Provision provision;

    @Mock
    private Umkm umkm;

    @Mock
    private Payment payment;

    @Test
    void testBillEntity() {
        assertNotNull(bill, "Bill entity should not be null");
    }

    @Test
    void testDebtField() {
        bill.setDebt(1000L);
        Long debt = bill.getDebt();
        assertNotNull(debt, "Debt should not be null");
    }

    @Test
    void testInterestField() {
        bill.setInterest(5);
        Integer interest = bill.getInterest();
        assertNotNull(interest, "Interest should not be null");
    }

    @Test
    void testDueDateField() {
        bill.setDueDate(LocalDate.now());
        LocalDate dueDate = bill.getDueDate();
        assertNotNull(dueDate, "DueDate should not be null");
    }

    @Test
    void testIsPaidField() {
        bill.setIsPaid(true);
        Boolean isPaid = bill.getIsPaid();
        assertNotNull(isPaid, "IsPaid should not be null");
    }

    @Test
    void testIsVerifyField() {
        bill.setIsVerify(true);
        Boolean isVerify = bill.getIsVerify();
        assertNotNull(isVerify, "IsVerify should not be null");
    }

    @Test
    void testProvisionRelationship() {
        bill.setProvision(provision);
        Provision associatedProvision = bill.getProvision();
        assertNotNull(associatedProvision, "Associated Provision should not be null");
    }

    @Test
    void testUmkmRelationship() {
        bill.setUmkm(umkm);
        Umkm associatedUmkm = bill.getUmkm();
        assertNotNull(associatedUmkm, "Associated Umkm should not be null");
    }

    @Test
    void testPaymentRelationship() {
        bill.setPayment(payment);
        Payment associatedPayment = bill.getPayment();
        assertNotNull(associatedPayment, "Associated Payment should not be null");
    }

    @Test
    void testToStringMethod() {
        assertNotNull(bill.toString(), "toString method should not return null");
    }
}
