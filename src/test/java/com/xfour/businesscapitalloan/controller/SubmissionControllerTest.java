package com.xfour.businesscapitalloan.controller;

import com.xfour.businesscapitalloan.model.request.NewSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.SearchSubmissionRequest;
import com.xfour.businesscapitalloan.model.request.UpdateSubmissionRequest;
import com.xfour.businesscapitalloan.model.response.CommonResponse;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import com.xfour.businesscapitalloan.service.SubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubmissionControllerTest {

    @InjectMocks
    private SubmissionController submissionController;

    private SubmissionService submissionService;

    @BeforeEach
    void setUp() {
        submissionService = mock(SubmissionService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        NewSubmissionRequest newSubmissionRequest = new NewSubmissionRequest();
        SubmissionResponse submissionResponse = new SubmissionResponse();
        when(submissionService.create(eq(newSubmissionRequest))).thenReturn(submissionResponse);

        ResponseEntity<?> responseEntity = submissionController.create(newSubmissionRequest);

        assertEquals(responseEntity.getStatusCodeValue(), 201);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(submissionResponse, commonResponse.getData());
    }

    @Test
    void getById() {
        String submissionId = "your_submission_id";
        SubmissionResponse submissionResponse = new SubmissionResponse();
        when(submissionService.getById(eq(submissionId))).thenReturn(submissionResponse);

        ResponseEntity<?> responseEntity = submissionController.getById(submissionId);

        assertEquals(responseEntity.getStatusCodeValue(), 200);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(submissionResponse, commonResponse.getData());
    }

    @Test
    void getAll() {
        SearchSubmissionRequest searchSubmissionRequest = new SearchSubmissionRequest();
        Page<SubmissionResponse> submissionResponses = mock(Page.class);
        when(submissionService.getAll(eq(searchSubmissionRequest))).thenReturn(submissionResponses);

        ResponseEntity<?> responseEntity = submissionController.getAll(
                searchSubmissionRequest.getMinLoanAmount(),
                searchSubmissionRequest.getMaxLoanAmount(),
                searchSubmissionRequest.getPage(),
                searchSubmissionRequest.getSize());

        assertEquals(responseEntity.getStatusCodeValue(), 200);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(submissionResponses.getContent(), commonResponse.getData());
    }

    @Test
    void update() {
        UpdateSubmissionRequest updateSubmissionRequest = new UpdateSubmissionRequest();
        SubmissionResponse submissionResponse = new SubmissionResponse();
        when(submissionService.update(eq(updateSubmissionRequest))).thenReturn(submissionResponse);

        ResponseEntity<?> responseEntity = submissionController.update(updateSubmissionRequest);

        assertEquals(responseEntity.getStatusCodeValue(), 200);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(submissionResponse, commonResponse.getData());
    }

    @Test
    void getAllSubmissionByDebtorId() {
        String debtorId = "your_debtor_id";
        List<SubmissionResponse> submissionResponses = mock(List.class);
        when(submissionService.getAllByDebtorId(eq(debtorId))).thenReturn(submissionResponses);

        ResponseEntity<?> responseEntity = submissionController.getAllSubmissionByDebtorId(debtorId);

        assertEquals(responseEntity.getStatusCodeValue(), 200);
        CommonResponse<?> commonResponse = (CommonResponse<?>) responseEntity.getBody();
        assertEquals(submissionResponses, commonResponse.getData());
    }
}
