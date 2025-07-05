package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.FormatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatRepository extends JpaRepository<FormatEntity, Long> {}