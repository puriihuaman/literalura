package com.puriihuaman.literalura.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;


@Entity
@Table(name = "Bookses")
@Getter
@Setter
public class Books {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(unique = true)
    private String title;

    @Column(columnDefinition = "text")
    private String summaries;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> bookshelves;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> languages;

    @Column
    private Boolean copyright;

    @Column
    private String mediaType;

    @Column(nullable = false)
    private UUID formats;

    @Column
    private Integer downloadCount;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> subjects;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> authors;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> translators;

    @OneToMany(mappedBy = "bookFormats")
    private Set<Formats> bookFormats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authors_books_id")
    private Authors authorsBooks;

}
