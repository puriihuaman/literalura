package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    Page<BookEntity> findByTitleContainingIgnoreCase(final String title, final Pageable pageable);
}