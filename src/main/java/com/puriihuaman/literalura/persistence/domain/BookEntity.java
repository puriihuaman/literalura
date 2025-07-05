package com.puriihuaman.literalura.persistence.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
@Entity(name = "Book")
public class BookEntity {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;
    
    @Column(columnDefinition = "Text")
    private String title;
    
    @Column(columnDefinition = "Text")
    private String summary;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "book_authors", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<AuthorEntity> authors;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "book_subjects", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_name", referencedColumnName = "name")
    )
    private Set<SubjectEntity> subjects;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "book_shelves", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shelf_name", referencedColumnName = "name")
    )
    private Set<ShelfEntity> shelves;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_languages", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "language_code", referencedColumnName = "code")
    )
    private Set<LanguageEntity> languages;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FormatEntity> formats;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_translators", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id", referencedColumnName = "id")
    )
    private Set<TranslatorEntity> translators;
    
    @Column
    private Boolean copyright;
    
    @Column(length = 20)
    private String mediaType;
    
    @Column
    private Long downloadCount;
    
    public void addAuthor(AuthorEntity author) {
        authors.add(author);
        author.getBooks().add(this);
    }
    
    public void addBookshelf(ShelfEntity bookshelf) {
        shelves.add(bookshelf);
        bookshelf.getBooks().add(this);
    }
    
    public void addSubject(SubjectEntity subject) {
        subjects.add(subject);
        subject.getBooks().add(this);
    }
    
    public void addLanguage(LanguageEntity language) {
        languages.add(language);
        language.getBooks().add(this);
    }
    
    public void addFormat(FormatEntity format) {
        formats.add(format);
        format.setBook(this);
    }
    
    public void addTranslator(TranslatorEntity translator) {
        translators.add(translator);
        translator.getBooks().add(this);
    }
}