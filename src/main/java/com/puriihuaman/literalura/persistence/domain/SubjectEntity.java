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
@Table(name = "subjects")
@Entity(name = "Subject")
public class SubjectEntity {
    @Id
    @Column(length = 200, unique = true, nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "subjects")
    private Set<BookEntity> books;
}