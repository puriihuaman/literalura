package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.domain.Books;
import com.puriihuaman.literalura.domain.Formats;
import com.puriihuaman.literalura.model.FormatsDTO;
import com.puriihuaman.literalura.repos.BooksRepository;
import com.puriihuaman.literalura.repos.FormatsRepository;
import com.puriihuaman.literalura.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FormatsService {

    private final FormatsRepository formatsRepository;
    private final BooksRepository booksRepository;

    public FormatsService(final FormatsRepository formatsRepository,
            final BooksRepository booksRepository) {
        this.formatsRepository = formatsRepository;
        this.booksRepository = booksRepository;
    }

    public List<FormatsDTO> findAll() {
        final List<Formats> formatses = formatsRepository.findAll(Sort.by("id"));
        return formatses.stream()
                .map(formats -> mapToDTO(formats, new FormatsDTO()))
                .toList();
    }

    public FormatsDTO get(final UUID id) {
        return formatsRepository.findById(id)
                .map(formats -> mapToDTO(formats, new FormatsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final FormatsDTO formatsDTO) {
        final Formats formats = new Formats();
        mapToEntity(formatsDTO, formats);
        return formatsRepository.save(formats).getId();
    }

    public void update(final UUID id, final FormatsDTO formatsDTO) {
        final Formats formats = formatsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(formatsDTO, formats);
        formatsRepository.save(formats);
    }

    public void delete(final UUID id) {
        formatsRepository.deleteById(id);
    }

    private FormatsDTO mapToDTO(final Formats formats, final FormatsDTO formatsDTO) {
        formatsDTO.setId(formats.getId());
        formatsDTO.setType(formats.getType());
        formatsDTO.setUrl(formats.getUrl());
        formatsDTO.setBookFormats(formats.getBookFormats() == null ? null : formats.getBookFormats().getId());
        return formatsDTO;
    }

    private Formats mapToEntity(final FormatsDTO formatsDTO, final Formats formats) {
        formats.setType(formatsDTO.getType());
        formats.setUrl(formatsDTO.getUrl());
        final Books bookFormats = formatsDTO.getBookFormats() == null ? null : booksRepository.findById(formatsDTO.getBookFormats())
                .orElseThrow(() -> new NotFoundException("bookFormats not found"));
        formats.setBookFormats(bookFormats);
        return formats;
    }

    public boolean typeExists(final String type) {
        return formatsRepository.existsByTypeIgnoreCase(type);
    }

}
