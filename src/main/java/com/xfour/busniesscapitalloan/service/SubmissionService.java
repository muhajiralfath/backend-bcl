package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.model.request.SubmissionRequest;
import com.xfour.busniesscapitalloan.model.response.SubmissionResponse;

import java.util.List;

public interface SubmissionService {
    SubmissionResponse create(SubmissionRequest request);
    List<SubmissionResponse> getAll();
    SubmissionResponse getById(String id);
    SubmissionResponse update(SubmissionRequest request);
}
