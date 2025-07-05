package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorResponseDTO(
        @JsonProperty("id")
        Long id,
        
        @JsonProperty("name")
        String name,
        
        @JsonProperty("birthYear")
        Integer birthYear,
        
        @JsonProperty("deathYear")
        Integer deathYear
) {}