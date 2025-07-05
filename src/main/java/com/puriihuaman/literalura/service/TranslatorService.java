package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.mapper.TranslatorMapper;
import com.puriihuaman.literalura.persistence.domain.TranslatorEntity;
import com.puriihuaman.literalura.persistence.dto.response.TranslatorResponseDTO;
import com.puriihuaman.literalura.persistence.repos.TranslatorRepository;
import com.puriihuaman.literalura.exception.ApiRequestException;
import com.puriihuaman.literalura.util.Specifications;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TranslatorService {
    private final TranslatorRepository translatorRepository;
    private final TranslatorMapper translatorMapper;
    
    public Page<TranslatorResponseDTO> findAll(final Map<String, String> keywords, final Pageable pageable) {
        try {
            Specification<TranslatorEntity> spec = Specifications.forTranslator(keywords);
            Page<TranslatorEntity> response = translatorRepository.findAll(spec, pageable);
            
            return response.map(translatorMapper::toDTO);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public TranslatorResponseDTO findById(final Long id) {
        try {
            Optional<TranslatorEntity> response = translatorRepository.findById(id);
            
            if (response.isEmpty()) {
                String message = "The translator with ID %d does not exist or has been deleted.".formatted(id);
                throw new ApiRequestException(ApiError.RECORD_NOT_FOUND, "Translator not found", message);
            }
            TranslatorEntity translator = response.get();
            
            return translatorMapper.toDTO(translator);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public Page<TranslatorResponseDTO> findByName(final String name, final Pageable pageable) {
        try {
            Page<TranslatorEntity> response = translatorRepository.findByNameContainingIgnoreCase(name, pageable);
            
            return response.map(translatorMapper::toDTO);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
}