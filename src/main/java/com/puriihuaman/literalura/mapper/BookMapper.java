package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.BookEntity;
import com.puriihuaman.literalura.persistence.dto.response.BookResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring", uses = {
        AuthorMapper.class,
        TranslatorMapper.class,
        FormatMapper.class,
        LanguageMapper.class,
        SubjectMapper.class,
        ShelfMapper.class
}
)
public interface BookMapper {
    @Mapping(target = "subjects", source = "subjects")
    @Mapping(target = "bookshelves", source = "shelves")
    @Mapping(target = "formats", source = "formats")
    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "translators", source = "translators")
    @Mapping(target = "languages", source = "languages")
    BookResponseDTO toDTO(final BookEntity book);
}