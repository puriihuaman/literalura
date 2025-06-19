package com.puriihuaman.literalura.repos;

import com.puriihuaman.literalura.domain.Books;
import com.puriihuaman.literalura.domain.Formats;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormatsRepository extends JpaRepository<Formats, UUID> {

    Formats findFirstByBookFormats(Books books);

    boolean existsByTypeIgnoreCase(String type);

}
