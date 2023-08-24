package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.model.request.SubmissionRequest;
import com.xfour.busniesscapitalloan.model.response.SubmissionResponse;
import org.springframework.data.domain.Page;


public interface SubmissionService {
    SubmissionResponse create(SubmissionRequest request);
    Page<SubmissionResponse> getAll();
    SubmissionResponse getById(String id);
    SubmissionResponse update(SubmissionRequest request);
}
