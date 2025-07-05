package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.FormatEntity;
import com.puriihuaman.literalura.persistence.dto.response.FormatResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormatTypeMapper.class})
public interface FormatMapper {
    @Mapping(target = "type", source = "formatType.name")
    FormatResponseDTO toDTO(final FormatEntity format);
}