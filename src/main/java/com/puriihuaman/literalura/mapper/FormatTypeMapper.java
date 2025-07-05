package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.FormatTypeEntity;
import com.puriihuaman.literalura.persistence.dto.response.FormatTypeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormatTypeMapper {
    FormatTypeResponseDTO toDTO(final FormatTypeEntity formatType);
}