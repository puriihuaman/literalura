package com.puriihuaman.literalura.util;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import com.puriihuaman.literalura.persistence.dto.response.PaginationDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApiResponseHandler {
    public static <T> ResponseEntity<ApiResponseData<T>> handleResponse(T data, ApiSuccess apiSuccess) {
        ApiResponseData<T> responseData = new ApiResponseData<>(data, apiSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    
    public static <T> ResponseEntity<ApiResponseData<T>> handleResponse(List<T> data, ApiSuccess apiSuccess) {
        ApiResponseData<T> responseData = new ApiResponseData<>(data, apiSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    
    public static <T> ResponseEntity<ApiResponseDataPagination<T>> handleResponse(Page<T> page, ApiSuccess apiSuccess) {
        PaginationDTO pagination = PaginationDTO.builder()
                                                .pageNumber(page.getPageable().getPageNumber())
                                                .pageSize(page.getPageable().getPageSize())
                                                .totalPages(page.getTotalPages())
                                                .totalElements((int) page.getTotalElements())
                                                .numberOfElements(page.getNumberOfElements())
                                                .first(page.isFirst())
                                                .last(page.isLast())
                                                .sorted(page.getSort().isSorted())
                                                .unsorted(page.getSort().isUnsorted())
                                                .empty(page.isEmpty())
                                                .build();
        
        ApiResponseDataPagination<T> responseData = new ApiResponseDataPagination<>(
                page.getContent(),
                pagination,
                apiSuccess
        );
        return new ResponseEntity<>(responseData, apiSuccess.getHttpStatus());
    }
}