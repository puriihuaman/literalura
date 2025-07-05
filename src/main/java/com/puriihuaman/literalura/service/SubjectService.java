package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.mapper.SubjectMapper;
import com.puriihuaman.literalura.persistence.domain.SubjectEntity;
import com.puriihuaman.literalura.persistence.dto.response.SubjectResponseDTO;
import com.puriihuaman.literalura.persistence.repos.SubjectRepository;
import com.puriihuaman.literalura.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    
    public List<SubjectResponseDTO> findAll() {
        try {
            List<SubjectEntity> subjects = subjectRepository.findAll();
            
            return subjectMapper.toDTOs(subjects);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public SubjectResponseDTO findByName(final String name) {
        try {
            Optional<SubjectEntity> subjects = subjectRepository.findById(name);
            
            if (subjects.isEmpty()) {
                String message = "The subject with the name '%s' does not exist or has been deleted.".formatted(name);
                throw new ApiRequestException(ApiError.RECORD_NOT_FOUND, "The subject not found", message);
            }
            return subjectMapper.toDTO(subjects.get());
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
}