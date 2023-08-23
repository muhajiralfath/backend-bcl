package com.xfour.busniesscapitalloan.repository;

import com.xfour.busniesscapitalloan.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String > {
}
