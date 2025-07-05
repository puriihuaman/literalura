package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public record BookResponseDTO(
        @JsonProperty(value = "id")
        Long id,
        
        @JsonProperty(value = "title")
        String title,
        
        @JsonProperty(value = "summary")
        String summary,
        
        @JsonProperty(value = "authors")
        Set<AuthorResponseDTO> authors,
        
        @JsonProperty(value = "subjects")
        Set<SubjectResponseDTO> subjects,
        
        @JsonProperty(value = "bookshelves")
        Set<ShelfResponseDTO> bookshelves,
        
        @JsonProperty(value = "languages")
        Set<LanguageResponseDTO> languages,
        
        @JsonProperty(value = "formats")
        List<FormatResponseDTO> formats,
        
        @JsonProperty(value = "translators")
        Set<TranslatorResponseDTO> translators,
        
        @JsonProperty(value = "copyright")
        Boolean copyright,
        
        @JsonProperty(value = "mediaType")
        String mediaType,
        
        @JsonProperty(value = "downloadCount")
        Long downloadCount
) {}