package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.ShelfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository
        extends JpaRepository<ShelfEntity, String>, JpaSpecificationExecutor<ShelfEntity> {}