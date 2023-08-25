package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.Provision;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvisionRepository extends JpaRepository<Provision, String >, JpaSpecificationExecutor<Provision> {
}
