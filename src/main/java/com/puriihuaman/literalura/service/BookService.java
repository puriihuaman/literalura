package com.puriihuaman.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.mapper.BookMapper;
import com.puriihuaman.literalura.persistence.domain.AuthorEntity;
import com.puriihuaman.literalura.persistence.domain.BookEntity;
import com.puriihuaman.literalura.persistence.domain.FormatEntity;
import com.puriihuaman.literalura.persistence.domain.FormatTypeEntity;
import com.puriihuaman.literalura.persistence.domain.LanguageEntity;
import com.puriihuaman.literalura.persistence.domain.ShelfEntity;
import com.puriihuaman.literalura.persistence.domain.SubjectEntity;
import com.puriihuaman.literalura.persistence.domain.TranslatorEntity;
import com.puriihuaman.literalura.persistence.dto.request.AuthorRequestDTO;
import com.puriihuaman.literalura.persistence.dto.request.BookRequestDTO;
import com.puriihuaman.literalura.persistence.dto.request.TranslatorRequestDTO;
import com.puriihuaman.literalura.persistence.dto.response.BookResponseDTO;
import com.puriihuaman.literalura.persistence.dto.response.ResponseDataDTO;
import com.puriihuaman.literalura.persistence.repos.AuthorRepository;
import com.puriihuaman.literalura.persistence.repos.BookRepository;
import com.puriihuaman.literalura.persistence.repos.FormatTypeRepository;
import com.puriihuaman.literalura.persistence.repos.LanguageRepository;
import com.puriihuaman.literalura.persistence.repos.ShelfRepository;
import com.puriihuaman.literalura.persistence.repos.SubjectRepository;
import com.puriihuaman.literalura.persistence.repos.TranslatorRepository;
import com.puriihuaman.literalura.exception.ApiRequestException;
import com.puriihuaman.literalura.util.Specifications;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository langRepository;
    private final SubjectRepository subjectRepository;
    private final ShelfRepository bookshelfRepository;
    private final TranslatorRepository translatorRepository;
    private final FormatTypeRepository formatTypeRepository;
    private final BookMapper bookMapper;
    
    @Transactional
    public List<String> saveBook(final Integer page) {
        List<String> listOfBookTitles = new ArrayList<>();
        try {
            String data = FetchingData.getData(page);
            ResponseDataDTO response = objectMapper.readValue(data, ResponseDataDTO.class);
            
            Map<String, LanguageEntity> existingLanguages = loadExistingLanguages();
            Map<String, SubjectEntity> existingSubjects = loadExistingSubjects();
            Map<String, ShelfEntity> existingBookshelves = loadExistingBookshelves();
            Map<String, AuthorEntity> existingAuthors = loadExistingAuthors();
            Map<String, TranslatorEntity> existingTranslators = loadExistingTranslators();
            
            for (BookRequestDTO bookDTO : response.results()) {
                if (bookRepository.existsById(bookDTO.id())) {
                    continue;
                }
                BookEntity newBook = mapToEntity(bookDTO);
                
                processAuthors(bookDTO, newBook, existingAuthors);
                processLanguages(bookDTO, newBook, existingLanguages);
                processSubjects(bookDTO, newBook, existingSubjects);
                processBookshelves(bookDTO, newBook, existingBookshelves);
                processTranslators(bookDTO, newBook, existingTranslators);
                processFormats(bookDTO, newBook);
                
                bookRepository.save(newBook);
                listOfBookTitles.add(bookDTO.title());
            }
            return listOfBookTitles;
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ApiRequestException(ApiError.DATABASE_CONSTRAINT_VIOLATION);
        } catch (JpaSystemException | TransactionSystemException ex) {
            throw new ApiRequestException(ApiError.PERSISTENCE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public Page<BookResponseDTO> findAll(final Map<String, String> keywords, final Pageable pageable) {
        try {
            Specification<BookEntity> spec = Specifications.forBook(keywords);
            Page<BookEntity> response = bookRepository.findAll(spec, pageable);
            
            return response.map(bookMapper::toDTO);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public BookResponseDTO getById(final Long id) {
        try {
            Optional<BookEntity> response = bookRepository.findById(id);
            
            if (response.isEmpty()) {
                String message = "The book with the ID '%d' was not found or was deleted.".formatted(id);
                throw new ApiRequestException(ApiError.RECORD_NOT_FOUND, "Book not found", message);
            }
            BookEntity book = response.get();
            
            return bookMapper.toDTO(book);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public Page<BookResponseDTO> findByTitle(final String title, final Pageable pageable) {
        try {
            Page<BookEntity> response = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
            
            return response.map(bookMapper::toDTO);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    private BookEntity mapToEntity(final BookRequestDTO book) {
        return BookEntity.builder()
                         .id(book.id())
                         .title(book.title())
                         .copyright(book.copyright())
                         .mediaType(book.mediaType())
                         .summary(book.summaries().toString())
                         .downloadCount(book.downloadCount())
                         .authors(new HashSet<>())
                         .languages(new HashSet<>())
                         .subjects(new HashSet<>())
                         .shelves(new HashSet<>())
                         .translators(new HashSet<>())
                         .formats(new ArrayList<>())
                         .build();
    }
    
    private void processAuthors(
            final BookRequestDTO bookDTO,
            final BookEntity book,
            final Map<String, AuthorEntity> existingAuthors
    ) {
        for (AuthorRequestDTO authorDTO : bookDTO.authors()) {
            AuthorEntity author = existingAuthors.computeIfAbsent(
                    authorDTO.name(), name -> {
                        AuthorEntity newAuthor = AuthorEntity.builder()
                                                             .name(name)
                                                             .birthYear(authorDTO.birthYear())
                                                             .deathYear(authorDTO.deathYear())
                                                             .books(new HashSet<>())
                                                             .build();
                        return authorRepository.save(newAuthor);
                    }
            );
            book.addAuthor(author);
        }
    }
    
    private void processLanguages(
            final BookRequestDTO bookDTO,
            final BookEntity book,
            final Map<String, LanguageEntity> existingLanguages
    ) {
        for (String languageCode : bookDTO.languages()) {
            LanguageEntity language = existingLanguages.computeIfAbsent(
                    languageCode, code -> {
                        LanguageEntity newLanguage = LanguageEntity.builder().code(code).books(new HashSet<>()).build();
                        return langRepository.save(newLanguage);
                    }
            );
            
            book.addLanguage(language);
        }
    }
    
    private void processSubjects(
            final BookRequestDTO bookDTO,
            final BookEntity book,
            final Map<String, SubjectEntity> existingSubjects
    ) {
        for (String subjectName : bookDTO.subjects()) {
            SubjectEntity subject = existingSubjects.computeIfAbsent(
                    subjectName, name -> {
                        SubjectEntity newSubject = SubjectEntity.builder().name(name).books(new HashSet<>()).build();
                        return subjectRepository.save(newSubject);
                    }
            );
            
            book.addSubject(subject);
        }
    }
    
    private void processBookshelves(
            final BookRequestDTO bookDTO,
            final BookEntity book,
            final Map<String, ShelfEntity> existingBookshelves
    ) {
        for (String bookshelfName : bookDTO.bookshelves()) {
            ShelfEntity bookshelf = existingBookshelves.computeIfAbsent(
                    bookshelfName, name -> {
                        ShelfEntity newBookshelf = ShelfEntity.builder().name(name).books(new HashSet<>()).build();
                        return bookshelfRepository.save(newBookshelf);
                    }
            );
            
            book.addBookshelf(bookshelf);
        }
    }
    
    private void processTranslators(
            final BookRequestDTO bookDTO,
            final BookEntity book,
            final Map<String, TranslatorEntity> existingTranslators
    ) {
        for (TranslatorRequestDTO translatorDTO : bookDTO.translators()) {
            TranslatorEntity translator = existingTranslators.computeIfAbsent(
                    translatorDTO.name(), name -> {
                        TranslatorEntity newTranslator =
                                TranslatorEntity.builder().name(name).birthYear(translatorDTO.birthYear()).deathYear(
                                        translatorDTO.deathYear()).books(new HashSet<>()).build();
                        return translatorRepository.save(newTranslator);
                    }
            );
            
            book.addTranslator(translator);
        }
    }
    
    private void processFormats(final BookRequestDTO bookDTO, final BookEntity book) {
        Map<String, String> formats = bookDTO.formats();
        
        for (Map.Entry<String, String> format : formats.entrySet()) {
            String formatType = format.getKey();
            String url = format.getValue();
            
            FormatTypeEntity formatTypeEntity = getOrCreateFormatType(formatType);
            
            FormatEntity newFormat = FormatEntity.builder().formatType(formatTypeEntity).url(url).build();
            newFormat.addBook(book);
            book.addFormat(newFormat);
        }
    }
    
    private FormatTypeEntity getOrCreateFormatType(final String formatTypeName) {
        return formatTypeRepository.findByName(formatTypeName).orElseGet(() -> {
            FormatTypeEntity newFormatType = FormatTypeEntity.builder().name(formatTypeName).build();
            return formatTypeRepository.save(newFormatType);
        });
    }
    
    private Map<String, LanguageEntity> loadExistingLanguages() {
        return langRepository.findAll().stream().collect(Collectors.toMap(
                LanguageEntity::getCode,
                Function.identity()
        ));
    }
    
    private Map<String, SubjectEntity> loadExistingSubjects() {
        return subjectRepository.findAll().stream().collect(Collectors.toMap(
                SubjectEntity::getName,
                Function.identity()
        ));
    }
    
    private Map<String, ShelfEntity> loadExistingBookshelves() {
        return bookshelfRepository.findAll().stream().collect(Collectors.toMap(
                ShelfEntity::getName,
                Function.identity()
        ));
    }
    
    private Map<String, AuthorEntity> loadExistingAuthors() {
        return authorRepository.findAll().stream().collect(Collectors.toMap(
                AuthorEntity::getName,
                Function.identity()
        ));
    }
    
    private Map<String, TranslatorEntity> loadExistingTranslators() {
        return translatorRepository.findAll().stream().collect(Collectors.toMap(
                TranslatorEntity::getName,
                Function.identity()
        ));
    }
}