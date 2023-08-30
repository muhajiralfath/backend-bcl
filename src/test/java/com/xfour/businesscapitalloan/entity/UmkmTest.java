package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UmkmTest {

    @InjectMocks
    private Umkm umkm;

    @Test
    void testUmkmEntity() {
        assertNotNull(umkm, "Umkm entity should not be null");
    }

    @Test
    void testNameField() {
        umkm.setName("Sample UMKM");
        assertEquals("Sample UMKM", umkm.getName(), "Name field should match the set value");
    }

    @Test
    void testNoSiupField() {
        umkm.setNoSiup("ABC123");
        assertEquals("ABC123", umkm.getNoSiup(), "NoSiup field should match the set value");
    }

    @Test
    void testAddressField() {
        umkm.setAddress("Sample Address");
        assertEquals("Sample Address", umkm.getAddress(), "Address field should match the set value");
    }

    @Test
    void testCapitalField() {
        umkm.setCapital(10000L);
        assertEquals(10000L, umkm.getCapital(), "Capital field should match the set value");
    }

    @Test
    void testUmkmTypeField() {
        umkm.setUmkmType("Type A");
        assertEquals("Type A", umkm.getUmkmType(), "UmkmType field should match the set value");
    }

    @Test
    void testBankAccountField() {
        umkm.setBankAccount("1234567890");
        assertEquals("1234567890", umkm.getBankAccount(), "BankAccount field should match the set value");
    }

    @Test
    void testDebtorField() {
        Debtor debtor = new Debtor();
        umkm.setDebtor(debtor);
        assertEquals(debtor, umkm.getDebtor(), "Debtor field should match the set value");
    }

    @Test
    void testDocumentField() {
        UmkmDocument document = new UmkmDocument();
        umkm.setDocument(document);
        assertEquals(document, umkm.getDocument(), "Document field should match the set value");
    }

    @Test
    void testIdField() {
        umkm.setId("123");
        assertNotNull(umkm.getId(), "Id field should not be null");
    }

    @Test
    void testCreatedAtField() {
    }

    @Test
    void testUpdatedAtField() {
    }

    @Test
    void testToStringMethod() {
        assertNotNull(umkm.toString(), "toString method should not return null");
    }
}
