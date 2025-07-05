package com.puriihuaman.literalura.persistence.repos;

import com.puriihuaman.literalura.persistence.domain.FormatTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormatTypeRepository extends JpaRepository<FormatTypeEntity, Long> {
    Optional<FormatTypeEntity> findByName(final String name);
}