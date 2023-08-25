package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, String> {
}
