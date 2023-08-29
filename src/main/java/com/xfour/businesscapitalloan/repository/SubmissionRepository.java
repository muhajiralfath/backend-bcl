package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Submission;
import com.xfour.businesscapitalloan.model.response.SubmissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String>, JpaSpecificationExecutor<Submission> {
    List<Submission> findAllByUmkmId(String id);
}
