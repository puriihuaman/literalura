package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import com.puriihuaman.literalura.persistence.dto.response.SubjectResponseDTO;
import com.puriihuaman.literalura.service.SubjectService;
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
@RequestMapping(value = "/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(name = "Subjects", description = "Endpoints responsible for managing subjects.")
public class SubjectResource {
    private final SubjectService subjectService;
    
    @ApiResponse(
            responseCode = "200", description = "Subjects successfully obtained.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.SIMPLE_EXAMPLE_GET_ALL_RESOURCE
                    )
            )
    )
    @GetMapping()
    public ResponseEntity<ApiResponseData<SubjectResponseDTO>> findAll() {
        List<SubjectResponseDTO> subjects = subjectService.findAll();
        
        return ApiResponseHandler.handleResponse(subjects, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Successfully obtained subject.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_GET_RESOURCE
                    )
            )
    )
    @GetMapping(value = "/{name}")
    public ResponseEntity<ApiResponseData<SubjectResponseDTO>> findByName(final @PathVariable String name) {
        SubjectResponseDTO subject = subjectService.findByName(name);
        
        return ApiResponseHandler.handleResponse(subject, ApiSuccess.RESOURCE_RETRIEVED);
    }
}