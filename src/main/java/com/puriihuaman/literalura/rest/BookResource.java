package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.commons.enums.ApiSuccess;
import com.puriihuaman.literalura.persistence.dto.response.BookResponseDTO;
import com.puriihuaman.literalura.service.BookService;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(name = "Books", description = "Endpoints responsible for managing books.")
public class BookResource {
    private final BookService bookService;
    
    @ApiResponse(
            responseCode = "201", description = "Book successfully saved.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.CREATED_EXAMPLE
                    )
            )
    )
    @GetMapping(value = "/save/book")
    public ResponseEntity<ApiResponseData<String>> saveBook(@RequestParam(required = false) final Integer page) {
        List<String> listOfBookTitles = bookService.saveBook(page);
        
        return ApiResponseHandler.handleResponse(listOfBookTitles, ApiSuccess.RESOURCE_CREATED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Books successfully obtained.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseDataPagination.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_OF_GETTING_RESOURCES_WITH_PAGINATION
                    )
            )
    )
    @GetMapping
    public ResponseEntity<ApiResponseDataPagination<BookResponseDTO>> getAllBooks(
            @RequestParam(required = false) final Map<String, String> keywords,
            @DefaultValue @PageableDefault(size = 15, direction = Sort.Direction.ASC, sort = {"id"})
            final Pageable defaultPageable
    ) {
        Pageable pageable = buildPageable(defaultPageable);
        Page<BookResponseDTO> bookToPage = bookService.findAll(keywords, pageable);
        
        return ApiResponseHandler.handleResponse(bookToPage, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Successfully obtained book.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseData.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_GET_RESOURCE
                    )
            )
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponseData<BookResponseDTO>> getBooks(@PathVariable(name = "id") final Long id) {
        BookResponseDTO book = bookService.getById(id);
        
        return ApiResponseHandler.handleResponse(book, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    @ApiResponse(
            responseCode = "200", description = "Books successfully obtained by title.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseDataPagination.class),
                    examples = @ExampleObject(
                            value = SwaggerResponseExample.EXAMPLE_OF_GETTING_RESOURCES_WITH_PAGINATION
                    )
            )
    )
    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ApiResponseDataPagination<BookResponseDTO>> getBooksByTitle(
            @PathVariable(name = "title") final String title,
            @DefaultValue @PageableDefault(size = 15, direction = Sort.Direction.ASC, sort = {"title"})
            final Pageable defaultPageable
    ) {
        Pageable pageable = buildPageable(defaultPageable);
        Page<BookResponseDTO> bookToPage = bookService.findByTitle(title, pageable);
        
        return ApiResponseHandler.handleResponse(bookToPage, ApiSuccess.RESOURCE_RETRIEVED);
    }
    
    private Pageable buildPageable(final Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
    }
}