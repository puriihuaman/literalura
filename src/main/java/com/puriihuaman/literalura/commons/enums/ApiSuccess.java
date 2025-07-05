package com.puriihuaman.literalura.commons.enums;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiSuccess {
    RESOURCE_RETRIEVED("Successful operation", HttpStatus.OK),
    RESOURCE_CREATED("Resource created successful.", HttpStatus.CREATED);
    
    private String message;
    private final HttpStatus httpStatus;
    
    @JsonSetter
    public void setMessage(final String message) {
        this.message = message;
    }
}