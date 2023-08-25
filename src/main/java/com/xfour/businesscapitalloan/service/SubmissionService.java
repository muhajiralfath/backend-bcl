package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import org.springframework.data.domain.Page;


public interface SubmissionService {
    SubmissionResponse create(NewSubmissionRequest request);
    Page<SubmissionResponse> getAll();
    SubmissionResponse getById(String id);
    SubmissionResponse update(NewSubmissionRequest request);
}
