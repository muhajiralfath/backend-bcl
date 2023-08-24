package com.xfour.busniesscapitalloan.service.impl;

import com.xfour.busniesscapitalloan.model.request.SubmissionRequest;
import com.xfour.busniesscapitalloan.model.response.SubmissionResponse;
import com.xfour.busniesscapitalloan.repository.SubmissionRepository;
import com.xfour.busniesscapitalloan.service.SubmissionService;
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
    public SubmissionResponse create(SubmissionRequest request) {
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
    public SubmissionResponse update(SubmissionRequest request) {
        return null;
    }
}
