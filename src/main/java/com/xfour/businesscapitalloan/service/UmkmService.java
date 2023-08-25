package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Umkm;
import com.xfour.businesscapitalloan.model.request.NewUmkmRequest;
import com.xfour.businesscapitalloan.model.request.SearchUmkmRequest;
import com.xfour.businesscapitalloan.model.request.UpdateUmkmRequest;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface UmkmService {
    Umkm findById(String id);
    UmkmResponse create(NewUmkmRequest request);
    UmkmResponse getById(String id);
    UmkmResponse getByDebtorId(String debtorId);
    UmkmResponse getByAuthentication(Authentication authentication);
    Page<UmkmResponse> getAll(SearchUmkmRequest request);
    UmkmResponse update(UpdateUmkmRequest request);
}
