package com.puriihuaman.literalura.exception;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.persistence.dto.response.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleApiRequestException(final ApiRequestException ex) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                                .hasError(ex.getHasError())
                                .title(ex.getApiError().getTitle())
                                .message(ex.getApiError().getMessage())
                                .status(ex.getApiError().getStatus().value())
                                .code(ex.getApiError().getStatus().getReasonPhrase())
                                .reasons(ex.getReasons())
                                .timestamp(ex.getTimestamp())
                                .build(), ex.getApiError().getStatus()
        );
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException() {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                                .hasError(true)
                                .title(ApiError.ENDPOINT_NOT_FOUND.getTitle())
                                .message(ApiError.ENDPOINT_NOT_FOUND.getMessage())
                                .status(ApiError.INTERNAL_SERVER_ERROR.getStatus().value())
                                .code(ApiError.ENDPOINT_NOT_FOUND.getStatus().getReasonPhrase())
                                .reasons(null)
                                .timestamp(LocalDateTime.now())
                                .build(), ApiError.ENDPOINT_NOT_FOUND.getStatus()
        );
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions() {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                                .hasError(true)
                                .title(ApiError.INTERNAL_SERVER_ERROR.getTitle())
                                .message(ApiError.INTERNAL_SERVER_ERROR.getMessage())
                                .status(ApiError.INTERNAL_SERVER_ERROR.getStatus()
                                                                      .value())
                                .code(ApiError.INTERNAL_SERVER_ERROR.getStatus().getReasonPhrase())
                                .reasons(null)
                                .timestamp(LocalDateTime.now())
                                .build(), ApiError.INTERNAL_SERVER_ERROR.getStatus()
        );
    }
}