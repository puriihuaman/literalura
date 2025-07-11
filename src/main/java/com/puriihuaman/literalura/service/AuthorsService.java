package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.mapper.AuthorMapper;
import com.puriihuaman.literalura.persistence.domain.AuthorEntity;
import com.puriihuaman.literalura.persistence.dto.response.AuthorResponseDTO;
import com.puriihuaman.literalura.persistence.repos.AuthorRepository;
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
public class AuthorsService {
    private final AuthorRepository authorsRepository;
    private final AuthorMapper authorMapper;
    
    public Page<AuthorResponseDTO> findAll(final Map<String, String> keywords, final Pageable pageable) {
        try {
            Specification<AuthorEntity> spec = Specifications.forAuthor(keywords);
            Page<AuthorEntity> response = authorsRepository.findAll(spec, pageable);
            
            return response.map(authorMapper::toDTO);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public AuthorResponseDTO findById(final Long id) {
        try {
            Optional<AuthorEntity> response = authorsRepository.findById(id);
            
            if (response.isEmpty()) {
                String message = "The author with ID %d does not exist or has been deleted.".formatted(id);
                throw new ApiRequestException(ApiError.RECORD_NOT_FOUND, "Author not found", message);
            }
            AuthorEntity author = response.get();
            
            return authorMapper.toDTO(author);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public Page<AuthorResponseDTO> findByName(final String name, final Pageable pageable) {
        try {
            Page<AuthorEntity> response = authorsRepository.findByNameContainingIgnoreCase(name, pageable);
            
            return response.map(authorMapper::toDTO);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
}