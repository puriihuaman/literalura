package com.puriihuaman.literalura.exception;

import com.puriihuaman.literalura.commons.enums.ApiError;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ApiRequestException extends RuntimeException {
    private final Boolean hasError = true;
    private Map<String, String> reasons = new HashMap<>();
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final ApiError apiError;
    
    public ApiRequestException(final ApiError apiError) {
        this.apiError = apiError;
    }
    
    public ApiRequestException(final ApiError apiError, final String message) {
        this.apiError = apiError;
        this.apiError.setMessage(message);
    }
    
    public ApiRequestException(
            final String title,
            final String message,
            final Map<String, String> reasons,
            final ApiError apiError
    ) {
        super(message);
        this.apiError = apiError;
        this.apiError.setTitle(title);
        this.reasons = reasons;
    }
    
    public ApiRequestException(final ApiError apiError, final String title, final String message) {
        this.apiError = apiError;
        this.apiError.setTitle(title);
        this.apiError.setMessage(message);
    }
}