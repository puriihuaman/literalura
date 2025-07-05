package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import com.puriihuaman.literalura.persistence.dto.response.TranslatorResponseDTO;
import com.puriihuaman.literalura.service.TranslatorService;
import com.puriihuaman.literalura.util.ApiResponseData;
import com.puriihuaman.literalura.util.ApiResponseDataPagination;
import com.puriihuaman.literalura.util.ApiResponseHandler;
import com.puriihuaman.literalura.util.SwaggerResponseExample;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/translators", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(name = "Translators", description = "Endpoints responsible for managing translators.")
public class TranslatorResource {
    private final TranslatorService translatorService;
    
    @ApiResponse(
            responseCode = "200", description = "Translators successfully obtained.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseDataPagination.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_OF_GETTING_RESOURCES_WITH_PAGINATION
                    )
            )
    )
    @GetMapping()
    public ResponseEntity<ApiResponseDataPagination<TranslatorResponseDTO>> findAll(
            @RequestParam(required = false) final Map<String, String> keywords,
            @DefaultValue @PageableDefault(size = 15, direction = Sort.Direction.ASC, sort = {"name"})
            final Pageable defaultPageable
    ) {
        Pageable pageable = buildPageable(defaultPageable);
        Page<TranslatorResponseDTO> translatorToPage = translatorService.findAll(keywords, pageable);
        
        return ApiResponseHandler.handleResponse(translatorToPage, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Successfully obtained translator.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_GET_RESOURCE
                    )
            )
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponseData<TranslatorResponseDTO>> findById(@PathVariable(name = "id") final Long id) {
        TranslatorResponseDTO translator = translatorService.findById(id);
        
        return ApiResponseHandler.handleResponse(translator, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Translators successfully obtained by name.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseDataPagination.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_OF_GETTING_RESOURCES_WITH_PAGINATION
                    )
            )
    )
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<ApiResponseDataPagination<TranslatorResponseDTO>> findByName(
            @PathVariable(name = "name") final String name,
            @DefaultValue @PageableDefault(size = 15, direction = Sort.Direction.ASC, sort = {"name"})
            final Pageable defaultPageable
    ) {
        Pageable pageable = buildPageable(defaultPageable);
        Page<TranslatorResponseDTO> translatorToPage = translatorService.findByName(name, pageable);
        
        return ApiResponseHandler.handleResponse(translatorToPage, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    private Pageable buildPageable(final Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
    }
}