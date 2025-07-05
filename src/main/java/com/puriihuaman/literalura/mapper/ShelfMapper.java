package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.ShelfEntity;
import com.puriihuaman.literalura.persistence.dto.response.ShelfResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelfMapper {
    ShelfResponseDTO toDTO(final ShelfEntity shelf);
    
    List<ShelfResponseDTO> toDTOs(final List<ShelfEntity> shelves);
}