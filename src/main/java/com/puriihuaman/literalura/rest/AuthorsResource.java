package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.model.AuthorsDTO;
import com.puriihuaman.literalura.service.AuthorsService;
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
@RequestMapping(value = "/api/authorss", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorsResource {

    private final AuthorsService authorsService;

    public AuthorsResource(final AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorsDTO>> getAllAuthorss() {
        return ResponseEntity.ok(authorsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorsDTO> getAuthors(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(authorsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createAuthors(@RequestBody @Valid final AuthorsDTO authorsDTO) {
        final UUID createdId = authorsService.create(authorsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateAuthors(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final AuthorsDTO authorsDTO) {
        authorsService.update(id, authorsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAuthors(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = authorsService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        authorsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
