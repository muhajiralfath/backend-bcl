package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PaymentTest {

    @InjectMocks
    private Payment payment;

    @Test
    void testPaymentEntity() {
        assertNotNull(payment, "Payment entity should not be null");
    }

    @Test
    void testUmkmField() {
        Umkm umkm = new Umkm();
        payment.setUmkm(umkm);
        assertNotNull(payment.getUmkm(), "Umkm field should not be null");
    }

    @Test
    void testBillField() {
        Bill bill = new Bill();
        payment.setBill(bill);
        assertNotNull(payment.getBill(), "Bill field should not be null");
    }

    @Test
    void testAmountField() {
        payment.setAmount(1000L);
        assertNotNull(payment.getAmount(), "Amount field should not be null");
    }

    @Test
    void testSnapUrlField() {
        payment.setSnapUrl("https://example.com/snap-url");
        assertNotNull(payment.getSnapUrl(), "SnapUrl field should not be null");
    }

    @Test
    void testSnapTokenField() {
        payment.setSnapToken("snap-token-123");
        assertNotNull(payment.getSnapToken(), "SnapToken field should not be null");
    }

    @Test
    void testToStringMethod() {
        assertNotNull(payment.toString(), "toString method should not return null");
    }
}
