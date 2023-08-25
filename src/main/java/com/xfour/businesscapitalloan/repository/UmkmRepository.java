package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Umkm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UmkmRepository extends JpaRepository<Umkm, String>, JpaSpecificationExecutor<Umkm> {
    Optional<Umkm> findFirstByDebtor_Id(String debtorId);
    Optional<Umkm> findFirstByDebtor_UserCredential_Id(String userId);
}
