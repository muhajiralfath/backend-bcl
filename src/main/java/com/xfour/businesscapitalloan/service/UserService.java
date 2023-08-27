package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.UserCredential;
import com.xfour.businesscapitalloan.model.response.FileResponse;
import com.xfour.businesscapitalloan.model.response.UserResponse;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException;
    UserCredential getByAuthentication(Authentication authentication);
    UserResponse getByToken(Authentication authentication);
    FileResponse updateProfilePicture(MultipartFile multipartFile);
    Resource downloadProfilePicture(String imageId);
    void deleteProfilePicture(String imageId);
}
