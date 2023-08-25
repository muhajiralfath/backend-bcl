package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Bill;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String >, JpaSpecificationExecutor<Bill> {
}