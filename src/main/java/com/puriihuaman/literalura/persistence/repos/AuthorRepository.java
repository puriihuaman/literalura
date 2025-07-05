package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>, JpaSpecificationExecutor<AuthorEntity> {
    Page<AuthorEntity> findByNameContainingIgnoreCase(final @Param("name") String name, final Pageable pageable);
}