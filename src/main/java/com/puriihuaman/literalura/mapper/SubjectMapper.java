package com.puriihuaman.literalura.mapper;

import com.puriihuaman.literalura.persistence.domain.SubjectEntity;
import com.puriihuaman.literalura.persistence.dto.response.SubjectResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectResponseDTO toDTO(final SubjectEntity subject);
    
    List<SubjectResponseDTO> toDTOs(final List<SubjectEntity> subjects);
}