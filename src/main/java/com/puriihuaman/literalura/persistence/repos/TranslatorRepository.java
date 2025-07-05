package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.TranslatorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository
        extends JpaRepository<TranslatorEntity, Long>, JpaSpecificationExecutor<TranslatorEntity> {
    Page<TranslatorEntity> findByNameContainingIgnoreCase(final String name, final Pageable pageable);
}