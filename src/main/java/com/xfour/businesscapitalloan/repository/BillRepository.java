package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String >, JpaSpecificationExecutor<Bill> {
    List<Bill> findAllByUmkmId(String umkmId);
    List<Bill> findAllByIsPaidFalseAndFineDateLessThan(Long timestamp);
}
