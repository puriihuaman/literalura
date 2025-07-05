package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository
        extends JpaRepository<SubjectEntity, String>, JpaSpecificationExecutor<SubjectEntity> {}