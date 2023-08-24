package com.xfour.busniesscapitalloan.repository;

import com.xfour.busniesscapitalloan.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, String> {
}
