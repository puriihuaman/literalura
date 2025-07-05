package com.puriihuaman.literalura.util;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import com.puriihuaman.literalura.persistence.dto.response.PaginationDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class ApiResponseDataPagination<T> extends ApiResponseData<T> {
    private final PaginationDTO pagination;
    
    public ApiResponseDataPagination(List<T> data, PaginationDTO pagination, ApiSuccess apiSuccess) {
        super(data, apiSuccess);
        this.pagination = pagination;
    }
}