package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FormatTypeResponseDTO(
        @JsonProperty(value = "id")
        Long id,
        
        @JsonProperty(value = "name")
        String name
) {}