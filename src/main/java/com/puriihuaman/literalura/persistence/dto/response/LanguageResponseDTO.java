package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LanguageResponseDTO(
        @JsonProperty(value = "code")
        String code
) {}