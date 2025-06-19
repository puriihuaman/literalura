package com.puriihuaman.literalura.rest;

import com.puriihuaman.literalura.model.FormatsDTO;
import com.puriihuaman.literalura.service.FormatsService;
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
@RequestMapping(value = "/api/formatss", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormatsResource {

    private final FormatsService formatsService;

    public FormatsResource(final FormatsService formatsService) {
        this.formatsService = formatsService;
    }

    @GetMapping
    public ResponseEntity<List<FormatsDTO>> getAllFormatss() {
        return ResponseEntity.ok(formatsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormatsDTO> getFormats(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(formatsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createFormats(@RequestBody @Valid final FormatsDTO formatsDTO) {
        final UUID createdId = formatsService.create(formatsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateFormats(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final FormatsDTO formatsDTO) {
        formatsService.update(id, formatsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFormats(@PathVariable(name = "id") final UUID id) {
        formatsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
