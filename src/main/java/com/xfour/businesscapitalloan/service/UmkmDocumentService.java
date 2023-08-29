package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.entity.UmkmDocument;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UmkmDocumentService {
    UmkmDocument create(Umkm umkm, MultipartFile multipartFile);
    UmkmDocument getById(String id);
    UmkmDocument getByUmkmId(String umkmId);
    Resource downloadDoc(String id);
    void deleteById(String id);
}
