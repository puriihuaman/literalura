package com.puriihuaman.literalura.persistence.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRequestDTO(
        @JsonAlias(value = "id")
        Long id,
        
        @JsonAlias(value = "title")
        String title,
        
        @JsonAlias(value = "authors")
        List<AuthorRequestDTO> authors,
        
        @JsonAlias(value = "summaries")
        List<String> summaries,
        
        @JsonAlias(value = "translators")
        List<TranslatorRequestDTO> translators,
        
        @JsonAlias(value = "subjects")
        List<String> subjects,
        
        @JsonAlias(value = "bookshelves")
        List<String> bookshelves,
        
        @JsonAlias(value = "languages")
        List<String> languages,
        
        @JsonAlias(value = "formats")
        Map<String, String> formats,
        
        @JsonAlias(value = "copyright")
        Boolean copyright,
        
        @JsonAlias(value = "media_type")
        String mediaType,
        
        @JsonAlias(value = "download_count")
        Long downloadCount
) {}