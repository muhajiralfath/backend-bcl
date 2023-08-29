package com.xfour.businesscapitalloan.repository;

import com.xfour.businesscapitalloan.entity.UmkmDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UmkmDocumentRepository extends JpaRepository<UmkmDocument, String> {
    Optional<UmkmDocument> findUmkmDocumentByUmkmId(String umkmId);
}
