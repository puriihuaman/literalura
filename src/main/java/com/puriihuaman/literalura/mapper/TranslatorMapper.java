package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.TranslatorEntity;
import com.puriihuaman.literalura.persistence.dto.response.TranslatorResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranslatorMapper {
    TranslatorResponseDTO toDTO(final TranslatorEntity translator);
}