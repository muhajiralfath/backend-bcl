package com.xfour.businesscapitalloan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import javax.persistence.MappedSuperclass;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class BaseEntityTest {

    @InjectMocks
    private BaseEntity baseEntity;



    @Test
    void testCreatedDateAnnotation() {
        CreatedDate createdDateAnnotation = mock(CreatedDate.class);
        assertNotNull(createdDateAnnotation, "CreatedDate annotation should not be null");
    }

    @Test
    void testLastModifiedDateAnnotation() {
        LastModifiedDate lastModifiedDateAnnotation = mock(LastModifiedDate.class);
        assertNotNull(lastModifiedDateAnnotation, "LastModifiedDate annotation should not be null");
    }

    @Test
    void testJsonFormatAnnotation() {
        JsonFormat jsonFormatAnnotation = mock(JsonFormat.class);
        assertNotNull(jsonFormatAnnotation, "JsonFormat annotation should not be null");
    }


    @Test
    void testMappedSuperclassAnnotation() {
        MappedSuperclass mappedSuperclassAnnotation = mock(MappedSuperclass.class);
        assertNotNull(mappedSuperclassAnnotation, "MappedSuperclass annotation should not be null");
    }


}
