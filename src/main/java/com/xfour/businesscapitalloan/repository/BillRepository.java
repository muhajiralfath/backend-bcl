package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String >, JpaSpecificationExecutor<Bill> {
    List<Bill> findAllByUmkmId(String umkmId);
}
