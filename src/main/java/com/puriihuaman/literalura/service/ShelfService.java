package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.mapper.ShelfMapper;
import com.puriihuaman.literalura.persistence.domain.ShelfEntity;
import com.puriihuaman.literalura.persistence.dto.response.ShelfResponseDTO;
import com.puriihuaman.literalura.persistence.repos.ShelfRepository;
import com.puriihuaman.literalura.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShelfService {
    private final ShelfRepository shelfRepository;
    private final ShelfMapper shelfMapper;
    
    public List<ShelfResponseDTO> findAll() {
        try {
            List<ShelfEntity> shelves = shelfRepository.findAll();
            
            return shelfMapper.toDTOs(shelves);
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ShelfResponseDTO findByName(final String name) {
        try {
            Optional<ShelfEntity> response = shelfRepository.findById(name);
            
            if (response.isEmpty()) {
                String message = "The shelf with the name '%s' was not found or was deleted.".formatted(name);
                throw new ApiRequestException(ApiError.RECORD_NOT_FOUND, "Shelf not found", message);
            }
            return shelfMapper.toDTO(response.get());
        } catch (ApiRequestException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new ApiRequestException(ApiError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR);
        }
    }
}