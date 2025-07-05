package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FormatResponseDTO(
        @JsonProperty(value = "id")
        Long id,
        
        @JsonProperty(value = "type")
        String type,
        
        @JsonProperty(value = "url")
        String url
) {}