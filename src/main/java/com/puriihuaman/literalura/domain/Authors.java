package com.puriihuaman.literalura.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "Authorses")
@Getter
@Setter
public class Authors {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column
    private Integer birthYear;

    @Column
    private Integer deathYear;

    @Column
    private String name;

    @OneToMany(mappedBy = "authorsBooks")
    private Set<Books> authorsBooks;

}
