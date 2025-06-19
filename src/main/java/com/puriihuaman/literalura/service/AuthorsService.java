package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.domain.Authors;
import com.puriihuaman.literalura.domain.Books;
import com.puriihuaman.literalura.model.AuthorsDTO;
import com.puriihuaman.literalura.repos.AuthorsRepository;
import com.puriihuaman.literalura.repos.BooksRepository;
import com.puriihuaman.literalura.util.NotFoundException;
import com.puriihuaman.literalura.util.ReferencedWarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AuthorsService {

    private final AuthorsRepository authorsRepository;
    private final BooksRepository booksRepository;

    public AuthorsService(final AuthorsRepository authorsRepository,
            final BooksRepository booksRepository) {
        this.authorsRepository = authorsRepository;
        this.booksRepository = booksRepository;
    }

    public List<AuthorsDTO> findAll() {
        final List<Authors> authorses = authorsRepository.findAll(Sort.by("id"));
        return authorses.stream()
                .map(authors -> mapToDTO(authors, new AuthorsDTO()))
                .toList();
    }

    public AuthorsDTO get(final UUID id) {
        return authorsRepository.findById(id)
                .map(authors -> mapToDTO(authors, new AuthorsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final AuthorsDTO authorsDTO) {
        final Authors authors = new Authors();
        mapToEntity(authorsDTO, authors);
        return authorsRepository.save(authors).getId();
    }

    public void update(final UUID id, final AuthorsDTO authorsDTO) {
        final Authors authors = authorsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(authorsDTO, authors);
        authorsRepository.save(authors);
    }

    public void delete(final UUID id) {
        authorsRepository.deleteById(id);
    }

    private AuthorsDTO mapToDTO(final Authors authors, final AuthorsDTO authorsDTO) {
        authorsDTO.setId(authors.getId());
        authorsDTO.setBirthYear(authors.getBirthYear());
        authorsDTO.setDeathYear(authors.getDeathYear());
        authorsDTO.setName(authors.getName());
        return authorsDTO;
    }

    private Authors mapToEntity(final AuthorsDTO authorsDTO, final Authors authors) {
        authors.setBirthYear(authorsDTO.getBirthYear());
        authors.setDeathYear(authorsDTO.getDeathYear());
        authors.setName(authorsDTO.getName());
        return authors;
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Authors authors = authorsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Books authorsBooksBooks = booksRepository.findFirstByAuthorsBooks(authors);
        if (authorsBooksBooks != null) {
            referencedWarning.setKey("authors.books.authorsBooks.referenced");
            referencedWarning.addParam(authorsBooksBooks.getId());
            return referencedWarning;
        }
        return null;
    }

}
