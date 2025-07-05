package com.puriihuaman.literalura.persistence.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TranslatorRequestDTO(
        @JsonAlias("name")
        String name,
        
        @JsonAlias(value = {"birth_year", "birthYear"})
        Integer birthYear,
        
        @JsonAlias(value = {"death_year", "deathYear"})
        Integer deathYear
) {}