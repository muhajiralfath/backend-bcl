package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String>, JpaSpecificationExecutor<Submission> {
}
