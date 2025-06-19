package com.puriihuaman.literalura.repos;

import com.puriihuaman.literalura.domain.Authors;
import com.puriihuaman.literalura.domain.Books;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BooksRepository extends JpaRepository<Books, UUID> {

    Books findFirstByAuthorsBooks(Authors authors);

    boolean existsByTitleIgnoreCase(String title);

}
