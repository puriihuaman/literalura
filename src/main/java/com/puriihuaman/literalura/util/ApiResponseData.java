package com.puriihuaman.literalura.util;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiResponseData<T> {
    private final Object data;
    private final Boolean hasError = false;
    private final String message;
    private final Integer statusCode;
    private final LocalDateTime timestamp = LocalDateTime.now();
    
    public ApiResponseData(T data, ApiSuccess apiSuccess) {
        this.data = data;
        this.message = apiSuccess.getMessage();
        this.statusCode = apiSuccess.getHttpStatus().value();
    }
    
    public ApiResponseData(List<T> data, ApiSuccess apiSuccess) {
        this.data = data;
        this.message = apiSuccess.getMessage();
        this.statusCode = apiSuccess.getHttpStatus().value();
    }
}