package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.busniesscapitalloan.model.response.SubmissionResponse;
import org.springframework.data.domain.Page;


public interface SubmissionService {
    SubmissionResponse create(NewSubmissionRequest request);
    Page<SubmissionResponse> getAll();
    SubmissionResponse getById(String id);
    SubmissionResponse update(NewSubmissionRequest request);
}
