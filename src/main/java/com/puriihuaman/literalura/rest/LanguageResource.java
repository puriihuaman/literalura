package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import com.puriihuaman.literalura.persistence.dto.response.LanguageResponseDTO;
import com.puriihuaman.literalura.service.LanguageService;
import com.puriihuaman.literalura.util.ApiResponseData;
import com.puriihuaman.literalura.util.ApiResponseHandler;
import com.puriihuaman.literalura.util.SwaggerResponseExample;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/languages", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(name = "Languages", description = "Endpoints responsible for managing languages.")
public class LanguageResource {
    private LanguageService languageService;
    
    @ApiResponse(
            responseCode = "200", description = "Languages successfully obtained.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.SIMPLE_EXAMPLE_GET_ALL_RESOURCE
                    )
            )
    )
    @GetMapping()
    public ResponseEntity<ApiResponseData<LanguageResponseDTO>> findAll() {
        List<LanguageResponseDTO> languages = languageService.findAll();
        
        return ApiResponseHandler.handleResponse(languages, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Successfully obtained language.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_GET_RESOURCE
                    )
            )
    )
    @GetMapping(value = "/{code}")
    public ResponseEntity<ApiResponseData<LanguageResponseDTO>> findByCode(@PathVariable final String code) {
        LanguageResponseDTO language = languageService.findByCode(code);
        
        return ApiResponseHandler.handleResponse(language, ApiSuccess.RESOURCE_RETRIEVED);
    }
}