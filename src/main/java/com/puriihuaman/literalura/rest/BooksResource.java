package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.model.BooksDTO;
import com.puriihuaman.literalura.service.BooksService;
import com.puriihuaman.literalura.util.ReferencedException;
import com.puriihuaman.literalura.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/bookss", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksResource {

    private final BooksService booksService;

    public BooksResource(final BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<List<BooksDTO>> getAllBookss() {
        return ResponseEntity.ok(booksService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksDTO> getBooks(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(booksService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createBooks(@RequestBody @Valid final BooksDTO booksDTO) {
        final UUID createdId = booksService.create(booksDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateBooks(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final BooksDTO booksDTO) {
        booksService.update(id, booksDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBooks(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = booksService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        booksService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
