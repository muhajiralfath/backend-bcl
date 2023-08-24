package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.entity.Debtor;
import com.xfour.busniesscapitalloan.model.request.SearchDebtorRequest;
import com.xfour.busniesscapitalloan.model.request.UpdateDebtorRequest;
import com.xfour.busniesscapitalloan.model.response.DebtorResponse;
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
