package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.File;
import com.xfour.businesscapitalloan.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Value("${business-capital-loan.image-path-url}")
    private String path;

    @Override
    public File create(MultipartFile multipartFile) {
        log.info("start of create file");
        if (multipartFile.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "file is required");

        if (!List.of("image/jpeg", "image/png", "application/pdf").contains(multipartFile.getContentType()))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "content type is not supported");

        try {
            Path directoryPath = Paths.get(path);
            Files.createDirectories(directoryPath);
            String fileName = String.format("%d_%s", System.currentTimeMillis(), multipartFile.getOriginalFilename());
            Path filePath = directoryPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath);
            log.info("end of create file");

            return File.builder()
                    .name(fileName)
                    .path(filePath.toString())
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .build();
        } catch (IOException | RuntimeException e) {
            log.info("error create file {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");
        }
    }

    @Override
    public Resource get(String path) {
        Path filePath = Paths.get(path);
        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            log.info("error getFile");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");
        }
    }

    @Override
    public void delete(String path) {
        log.info("start removeFile");
        try {
            Path filePath = Paths.get(path);
            boolean exist = Files.deleteIfExists(filePath);
            if (!exist) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "file not found");
        } catch (IOException | RuntimeException e) {
            log.info("error removeFile {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");
        }
        log.info("end removeFile");
    }
}
