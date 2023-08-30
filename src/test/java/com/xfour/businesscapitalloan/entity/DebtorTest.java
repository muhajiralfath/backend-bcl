package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DebtorTest {

    @InjectMocks
    private Debtor debtor;

    @Mock
    private Umkm umkm;

    @Mock
    private UserCredential userCredential;

    @Test
    void testDebtorEntity() {
        assertNotNull(debtor, "Debtor entity should not be null");
    }

    @Test
    void testNikField() {
        debtor.setNik("1234567890");
        String nik = debtor.getNik();
        assertNotNull(nik, "NIK should not be null");
    }

    @Test
    void testNpwpField() {
        debtor.setNpwp("1234567890");
        String npwp = debtor.getNpwp();
        assertNotNull(npwp, "NPWP should not be null");
    }

    @Test
    void testNameField() {
        debtor.setName("John Doe");
        String name = debtor.getName();
        assertNotNull(name, "Name should not be null");
    }

    @Test
    void testHandphoneField() {
        debtor.setHandphone("1234567890");
        String handphone = debtor.getHandphone();
        assertNotNull(handphone, "Handphone should not be null");
    }

    @Test
    void testBirthPlaceField() {
        debtor.setBirthPlace("City");
        String birthPlace = debtor.getBirthPlace();
        assertNotNull(birthPlace, "BirthPlace should not be null");
    }

    @Test
    void testBirthDateField() {
        debtor.setBirthDate(new Date());
        Date birthDate = debtor.getBirthDate();
        assertNotNull(birthDate, "BirthDate should not be null");
    }

    @Test
    void testGenderField() {
        debtor.setGender("Male");
        String gender = debtor.getGender();
        assertNotNull(gender, "Gender should not be null");
    }

    @Test
    void testStatusField() {
        debtor.setStatus("Single");
        String status = debtor.getStatus();
        assertNotNull(status, "Status should not be null");
    }

    @Test
    void testAddressField() {
        debtor.setAddress("123 Main St");
        String address = debtor.getAddress();
        assertNotNull(address, "Address should not be null");
    }

    @Test
    void testJobField() {
        debtor.setJob("Engineer");
        String job = debtor.getJob();
        assertNotNull(job, "Job should not be null");
    }

    @Test
    void testUmkmRelationship() {
        debtor.setUmkm(umkm);
        Umkm associatedUmkm = debtor.getUmkm();
        assertNotNull(associatedUmkm, "Associated Umkm should not be null");
    }

    @Test
    void testUserCredentialRelationship() {
        debtor.setUserCredential(userCredential);
        UserCredential associatedUserCredential = debtor.getUserCredential();
        assertNotNull(associatedUserCredential, "Associated UserCredential should not be null");
    }

    @Test
    void testToStringMethod() {
        assertNotNull(debtor.toString(), "toString method should not return null");
    }
}
