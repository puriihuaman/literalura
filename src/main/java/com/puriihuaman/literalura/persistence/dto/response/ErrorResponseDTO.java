package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorResponseDTO(
        @JsonProperty(defaultValue = "true")
        Boolean hasError,
        String title,
        String message,
        Integer status,
        String code,
        Map<String, String> reasons,
        LocalDateTime timestamp
) {}