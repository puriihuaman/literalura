package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository
        extends JpaRepository<LanguageEntity, String>, JpaSpecificationExecutor<LanguageEntity> {}