package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.FileResponse;
import com.xfour.businesscapitalloan.model.response.UserResponse;
import com.xfour.businesscapitalloan.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get self user info")
    @GetMapping(path = "/me")
    public ResponseEntity<?> getByToken(Authentication authentication) {
        log.info("start getUserByToken");
        UserResponse userResponse = userService.getByToken(authentication);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(userResponse)
                .build();
        log.info("end getUserByToken");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Upload Profile Picture")
    @PutMapping(path = "/profile-picture")
    public ResponseEntity<?> updateProfilePicture(@RequestPart(name = "image")MultipartFile multipartFile) {
        log.info("start updateProfilePicture");
        FileResponse fileResponse = userService.updateProfilePicture(multipartFile);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data(fileResponse)
                .build();
        log.info("end updateProfilePicture");

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

    @Operation(summary = "Download Profile Picture")
    @GetMapping(path = "/profile-picture/{imageId}")
    public ResponseEntity<?> downloadProfilePicture(@PathVariable(name = "imageId") String imageId) {
        log.info("start downloadProfilePicture");
        Resource resource = userService.downloadProfilePicture(imageId);
        String contentType = determineContentType(Objects.requireNonNull(resource.getFilename()));
        log.info("end downloadProfilePicture");

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete Profile Picture")
    @DeleteMapping(path = "/profile-picture/{imageId}")
    public ResponseEntity<?> deleteProfilePicture(@PathVariable(name = "imageId") String imageId) {
        log.info("start deleteProfilePicture");
        userService.deleteProfilePicture(imageId);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .data("DELETE SUCCESS")
                .build();
        log.info("end deleteProfilePicture");

        return ResponseEntity.ok(commonResponse);
    }

    private String determineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        switch (extension) {
            case "png":
                return MimeTypeUtils.IMAGE_PNG_VALUE;
            case "jpg":
            case "jpeg":
                return MimeTypeUtils.IMAGE_JPEG_VALUE;
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}
