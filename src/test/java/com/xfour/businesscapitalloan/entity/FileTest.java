package com.xfour.businesscapitalloan.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class FileTest {

    @InjectMocks
    private File file;

    @Test
    void testFileEntity() {
        assertNotNull(file, "File entity should not be null");
    }

    @Test
    void testNameField() {
        file.setName("file.txt");
        String name = file.getName();
        assertNotNull(name, "Name should not be null");
    }

    @Test
    void testContentTypeField() {
        file.setContentType("text/plain");
        String contentType = file.getContentType();
        assertNotNull(contentType, "ContentType should not be null");
    }

    @Test
    void testPathField() {
        file.setPath("/path/to/file.txt");
        String path = file.getPath();
        assertNotNull(path, "Path should not be null");
    }

    @Test
    void testSizeField() {
        file.setSize(1024L);
        Long size = file.getSize();
        assertNotNull(size, "Size should not be null");
    }

    @Test
    void testToStringMethod() {
        assertNotNull(file.toString(), "toString method should not return null");
    }
}
