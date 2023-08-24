package com.xfour.busniesscapitalloan.repository;

import com.xfour.busniesscapitalloan.entity.Umkm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UmkmRepository extends JpaRepository<Umkm, String>, JpaSpecificationExecutor<Umkm> {

}
