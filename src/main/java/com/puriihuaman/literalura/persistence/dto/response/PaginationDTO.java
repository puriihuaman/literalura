package com.puriihuaman.literalura.persistence.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PaginationDTO(
        @JsonProperty(value = "pageNumber")
        Integer pageNumber,
        
        @JsonProperty(value = "pageSize")
        Integer pageSize,
        
        @JsonProperty(value = "totalPages")
        Integer totalPages,
        
        @JsonProperty(value = "totalElements")
        Integer totalElements,
        
        @JsonProperty(value = "numberOfElements")
        Integer numberOfElements,
        
        @JsonProperty(value = "first")
        Boolean first,
        
        @JsonProperty(value = "last")
        Boolean last,
        
        @JsonProperty(value = "sorted")
        Boolean sorted,
        
        @JsonProperty(value = "unsorted")
        Boolean unsorted,
        
        @JsonProperty(value = "empty")
        Boolean empty
) {}