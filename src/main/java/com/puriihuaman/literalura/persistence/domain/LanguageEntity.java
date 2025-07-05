package com.puriihuaman.literalura.persistence.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "languages")
@Entity(name = "Language")
public class LanguageEntity {
    @Id
    @Column(length = 6, unique = true, nullable = false, updatable = false)
    private String code;
    
    @ManyToMany(mappedBy = "languages", fetch = FetchType.EAGER)
    private Set<BookEntity> books;
}