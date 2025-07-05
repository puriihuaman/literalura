package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.mapper.LanguageMapper;
import com.puriihuaman.literalura.persistence.domain.LanguageEntity;
import com.puriihuaman.literalura.persistence.dto.response.LanguageResponseDTO;
import com.puriihuaman.literalura.persistence.repos.LanguageRepository;
import com.puriihuaman.literalura.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;
    
    public List<LanguageResponseDTO> findAll() {
        try {
            List<LanguageEntity> languages = languageRepository.findAll();
            
            return languageMapper.toDTOs(languages);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public LanguageResponseDTO findByCode(final String code) {
        try {
            Optional<LanguageEntity> response = languageRepository.findById(code);
            
            if (response.isEmpty()) {
                String message = "The language with the code '%s' was not found or was deleted.".formatted(code);
                throw new ApiRequestException(ApiError.RECORD_NOT_FOUND, "Language not found", message);
            }
            return languageMapper.toDTO(response.get());
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
}