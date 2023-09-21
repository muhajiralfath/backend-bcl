package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.entity.UmkmDocument;
import com.xfour.businesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.businesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.businesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UmkmService {
    Umkm findById(String id);
    UmkmResponse create(NewUmkmRequest request);
    UmkmResponse getById(String id);
    UmkmResponse getByDebtorId(String debtorId);
    UmkmResponse getByAuthentication(Authentication authentication);
    Page<UmkmResponse> getAll(SearchUmkmRequest request);
    UmkmResponse update(UpdateUmkmRequest request);
    UmkmDocument uploadDocument(Authentication authentication, MultipartFile multipartFile);
    Resource downloadDocument(Authentication authentication);
    Resource downloadDocumentById(String id);
}
