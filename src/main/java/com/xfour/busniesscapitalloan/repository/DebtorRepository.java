package com.xfour.busniesscapitalloan.repository;

import com.xfour.busniesscapitalloan.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, String>, JpaSpecificationExecutor<Debtor> {
    Optional<Debtor> findFirstByUserCredential_Id(String userId);
}
