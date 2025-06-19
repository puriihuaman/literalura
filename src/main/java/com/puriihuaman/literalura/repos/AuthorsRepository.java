package com.puriihuaman.literalura.repos;

import com.puriihuaman.literalura.domain.Authors;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorsRepository extends JpaRepository<Authors, UUID> {
}
