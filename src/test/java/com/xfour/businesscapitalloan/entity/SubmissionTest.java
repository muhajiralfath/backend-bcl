package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubmissionTest {

    @InjectMocks
    private Submission submission;

    @Test
    void testSubmissionEntity() {
        assertNotNull(submission, "Submission entity should not be null");
    }

    @Test
    void testTenorField() {
        submission.setTenor(12);
        assertEquals(12, submission.getTenor(), "Tenor field should match the set value");
    }

    @Test
    void testLoanAmountField() {
        submission.setLoanAmount(10000L);
        assertEquals(10000L, submission.getLoanAmount(), "LoanAmount field should match the set value");
    }

    @Test
    void testIsApproveField() {
        submission.setIsApprove(true);
        assertTrue(submission.getIsApprove(), "IsApprove field should match the set value");
    }

    @Test
    void testUmkmField() {
        Umkm umkm = new Umkm();
        submission.setUmkm(umkm);
        assertEquals(umkm, submission.getUmkm(), "Umkm field should match the set value");
    }

    @Test
    void testProvisionField() {
        Provision provision = new Provision();
        submission.setProvision(provision);
        assertEquals(provision, submission.getProvision(), "Provision field should match the set value");
    }

    @Test
    void testIdField() {
        submission.setId("123");
        assertNotNull(submission.getId(), "Id field should not be null");
    }

    @Test
    void testCreatedAtField() {
    }

    @Test
    void testUpdatedAtField() {
    }

    @Test
    void testToStringMethod() {
        assertNotNull(submission.toString(), "toString method should not return null");
    }
}
