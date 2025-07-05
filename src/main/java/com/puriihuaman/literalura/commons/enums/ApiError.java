package com.puriihuaman.literalura.commons.enums;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiError {
    BAD_REQUEST(
            HttpStatus.BAD_REQUEST,
            "Bad Request",
            "The request does not meet basic syntax or parameter requirements."
    ),
    INVALID_REQUEST_DATA(
            HttpStatus.BAD_REQUEST,
            "Invalid request data",
            "The requested data contains invalid values or is in an incorrect format."
    ),
    RECORD_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "Resource not found",
            "The requested resource does not exist or has been deleted."
    ),
    ENDPOINT_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "Route not available",
            "The requested URL does not correspond to any available resource."
    ),
    DATABASE_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Persistence error",
            "A critical operation with the data storage system failed."
    ),
    EXTERNAL_SERVICE_UNAVAILABLE(
            HttpStatus.SERVICE_UNAVAILABLE,
            "External service unavailable",
            "There was a problem connecting to an external service. Please try again later."
    ),
    DATABASE_CONSTRAINT_VIOLATION(
            HttpStatus.CONFLICT,
            "Database constraint violation",
            "A constraint was violated when trying to save the data (e.g., unique, not null, or foreign key constraint)."
    ),
    PERSISTENCE_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Database error",
            "An error occurred while trying to persist data into the database."
    ),
    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "System error",
            "An unexpected error occurred. Please try again later."
    );
    
    private HttpStatus status;
    private String title;
    private String message;
    
    @JsonSetter
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
    @JsonGetter
    public void setTitle(String title) {
        this.title = title;
    }
    
    @JsonGetter
    public void setMessage(String message) {
        this.message = message;
    }
}