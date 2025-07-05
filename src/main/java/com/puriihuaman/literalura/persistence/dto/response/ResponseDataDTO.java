package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.puriihuaman.literalura.persistence.dto.request.BookRequestDTO;

import java.util.List;

public record ResponseDataDTO(
        @JsonProperty(value = "count")
        Long count,
        
        @JsonProperty(value = "next")
        String next,
        
        @JsonProperty(value = "previous")
        String previous,
        
        @JsonProperty(value = "results")
        List<BookRequestDTO> results
) {}