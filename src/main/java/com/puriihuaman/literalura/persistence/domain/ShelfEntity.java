package com.puriihuaman.literalura.persistence.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Shelves")
@Entity(name = "Shelf")
public class ShelfEntity {
    @Id
    @Column(unique = true, nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "shelves")
    private Set<BookEntity> books;
}