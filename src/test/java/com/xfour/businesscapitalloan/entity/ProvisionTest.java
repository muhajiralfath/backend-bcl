package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProvisionTest {

    @InjectMocks
    private Provision provision;

    @Test
    void testProvisionEntity() {
        assertNotNull(provision, "Provision entity should not be null");
    }

    @Test
    void testSubmissionField() {
        Submission submission = new Submission();
        provision.setSubmission(submission);
        assertNotNull(provision.getSubmission(), "Submission field should not be null");
    }

    @Test
    void testIdField() {
        provision.setId("123");
        assertNotNull(provision.getId(), "Id field should not be null");
    }

    @Test
    void testCreatedAtField() {
    }

    @Test
    void testUpdatedAtField() {
    }

    @Test
    void testToStringMethod() {
        assertNotNull(provision.toString(), "toString method should not return null");
    }
}
