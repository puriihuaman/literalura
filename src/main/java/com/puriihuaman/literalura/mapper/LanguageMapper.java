package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.LanguageEntity;
import com.puriihuaman.literalura.persistence.dto.response.LanguageResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    LanguageResponseDTO toDTO(final LanguageEntity language);
    
    List<LanguageResponseDTO> toDTOs(final List<LanguageEntity> languages);
}