package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.AuthorEntity;
import com.puriihuaman.literalura.persistence.dto.response.AuthorResponseDTO;
import org.mapstruct.Mapper;
// ✅ complete
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDTO toDTO(final AuthorEntity author);
}