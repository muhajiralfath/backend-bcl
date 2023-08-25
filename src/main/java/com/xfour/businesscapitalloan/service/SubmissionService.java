package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Submission;
import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.SearchSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.UpdateSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import org.springframework.data.domain.Page;


public interface SubmissionService {
    Submission findById(String id);
    SubmissionResponse create(NewSubmissionRequest request);
    Page<SubmissionResponse> getAll(SearchSubmissionRequest request);
    SubmissionResponse getById(String id);
    SubmissionResponse update(UpdateSubmissionRequest request);
}
