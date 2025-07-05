package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShelfResponseDTO(
        @JsonProperty(value = "name")
        String name
) {}