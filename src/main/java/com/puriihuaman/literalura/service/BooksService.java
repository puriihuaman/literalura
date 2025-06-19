package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.domain.Authors;
import com.puriihuaman.literalura.domain.Books;
import com.puriihuaman.literalura.domain.Formats;
import com.puriihuaman.literalura.model.BooksDTO;
import com.puriihuaman.literalura.repos.AuthorsRepository;
import com.puriihuaman.literalura.repos.BooksRepository;
import com.puriihuaman.literalura.repos.FormatsRepository;
import com.puriihuaman.literalura.util.NotFoundException;
import com.puriihuaman.literalura.util.ReferencedWarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BooksService {

    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final FormatsRepository formatsRepository;

    public BooksService(final BooksRepository booksRepository,
            final AuthorsRepository authorsRepository, final FormatsRepository formatsRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.formatsRepository = formatsRepository;
    }

    public List<BooksDTO> findAll() {
        final List<Books> bookses = booksRepository.findAll(Sort.by("id"));
        return bookses.stream()
                .map(books -> mapToDTO(books, new BooksDTO()))
                .toList();
    }

    public BooksDTO get(final UUID id) {
        return booksRepository.findById(id)
                .map(books -> mapToDTO(books, new BooksDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final BooksDTO booksDTO) {
        final Books books = new Books();
        mapToEntity(booksDTO, books);
        return booksRepository.save(books).getId();
    }

    public void update(final UUID id, final BooksDTO booksDTO) {
        final Books books = booksRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(booksDTO, books);
        booksRepository.save(books);
    }

    public void delete(final UUID id) {
        booksRepository.deleteById(id);
    }

    private BooksDTO mapToDTO(final Books books, final BooksDTO booksDTO) {
        booksDTO.setId(books.getId());
        booksDTO.setTitle(books.getTitle());
        booksDTO.setSummaries(books.getSummaries());
        booksDTO.setBookshelves(books.getBookshelves());
        booksDTO.setLanguages(books.getLanguages());
        booksDTO.setCopyright(books.getCopyright());
        booksDTO.setMediaType(books.getMediaType());
        booksDTO.setFormats(books.getFormats());
        booksDTO.setDownloadCount(books.getDownloadCount());
        booksDTO.setSubjects(books.getSubjects());
        booksDTO.setAuthors(books.getAuthors());
        booksDTO.setTranslators(books.getTranslators());
        booksDTO.setAuthorsBooks(books.getAuthorsBooks() == null ? null : books.getAuthorsBooks().getId());
        return booksDTO;
    }

    private Books mapToEntity(final BooksDTO booksDTO, final Books books) {
        books.setTitle(booksDTO.getTitle());
        books.setSummaries(booksDTO.getSummaries());
        books.setBookshelves(booksDTO.getBookshelves());
        books.setLanguages(booksDTO.getLanguages());
        books.setCopyright(booksDTO.getCopyright());
        books.setMediaType(booksDTO.getMediaType());
        books.setFormats(booksDTO.getFormats());
        books.setDownloadCount(booksDTO.getDownloadCount());
        books.setSubjects(booksDTO.getSubjects());
        books.setAuthors(booksDTO.getAuthors());
        books.setTranslators(booksDTO.getTranslators());
        final Authors authorsBooks = booksDTO.getAuthorsBooks() == null ? null : authorsRepository.findById(booksDTO.getAuthorsBooks())
                .orElseThrow(() -> new NotFoundException("authorsBooks not found"));
        books.setAuthorsBooks(authorsBooks);
        return books;
    }

    public boolean titleExists(final String title) {
        return booksRepository.existsByTitleIgnoreCase(title);
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Books books = booksRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Formats bookFormatsFormats = formatsRepository.findFirstByBookFormats(books);
        if (bookFormatsFormats != null) {
            referencedWarning.setKey("books.formats.bookFormats.referenced");
            referencedWarning.addParam(bookFormatsFormats.getId());
            return referencedWarning;
        }
        return null;
    }

}
