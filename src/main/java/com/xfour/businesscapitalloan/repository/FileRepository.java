package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String > {
}
