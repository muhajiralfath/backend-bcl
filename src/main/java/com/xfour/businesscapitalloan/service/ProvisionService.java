package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Provision;
import com.xfour.businesscapitalloan.model.request.SearchProvisionRequest;
import com.xfour.businesscapitalloan.model.response.ProvisionResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface ProvisionService {
    Provision findById(String id);
    ProvisionResponse create(Provision provision);
    Page<ProvisionResponse> getAll(SearchProvisionRequest request);
    ProvisionResponse getById(String id);
}
