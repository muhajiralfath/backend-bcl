package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Debtor;
import com.xfour.businesscapitalloan.model.request.SearchDebtorRequest;
import com.xfour.businesscapitalloan.model.request.UpdateDebtorRequest;
import com.xfour.businesscapitalloan.model.response.DebtorResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface DebtorService {
    Debtor create(Debtor debtor);
    Debtor get(String id);
    DebtorResponse getByAuthentication(Authentication authentication);
    DebtorResponse getById(String id);
    Page<DebtorResponse> getAll(SearchDebtorRequest request);
    DebtorResponse update(UpdateDebtorRequest request);
}
