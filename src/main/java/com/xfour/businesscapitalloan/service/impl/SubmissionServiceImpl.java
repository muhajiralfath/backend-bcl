package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import com.xfour.businesscapitalloan.repository.SubmissionRepository;
import com.xfour.businesscapitalloan.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;

    @Override
    public SubmissionResponse create(NewSubmissionRequest request) {
        return null;
    }

    @Override
    public Page<SubmissionResponse> getAll() {
        return null;
    }

    @Override
    public SubmissionResponse getById(String id) {
        return null;
    }

    @Override
    public SubmissionResponse update(NewSubmissionRequest request) {
        return null;
    }
}
