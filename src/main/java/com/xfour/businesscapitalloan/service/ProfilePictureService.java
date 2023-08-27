package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.ProfilePicture;
import com.xfour.businesscapitalloan.entity.UserCredential;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePictureService {
    ProfilePicture create(UserCredential userCredential, MultipartFile multipartFile);
    Resource download(String id);
    void deleteById(String id);
}
