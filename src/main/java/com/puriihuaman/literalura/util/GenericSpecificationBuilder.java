package com.puriihuaman.literalura.util;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.exception.ApiRequestException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericSpecificationBuilder<T> {
    private final Map<String, SpecificationCriterion<T>> criteria;
    
    public GenericSpecificationBuilder(final Map<String, SpecificationCriterion<T>> criteria) {
        this.criteria = criteria;
    }
    
    public Specification<T> buildSpecification(Map<String, String> keywords) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Map<String, String> filters = new HashMap<>(keywords);
            
            filters.remove("page");
            filters.remove("size");
            filters.remove("sort");
            
            Set<String> allowedKeys = criteria.keySet();
            if (!allowedKeys.containsAll(filters.keySet())) {
                filters.keySet().removeAll(allowedKeys);
                
                String message = "Invalid parameter. Allowed values: %s.".formatted(allowedKeys.toString());
                throw new ApiRequestException(ApiError.INVALID_REQUEST_DATA, "Invalid parameter", message);
            }
            
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                if (value == null || value.trim().isEmpty()) continue;
                
                try {
                    SpecificationCriterion<T> specCriterion = criteria.get(key);
                    if (specCriterion != null) {
                        predicates.add(specCriterion.toPredicate(root, cb, value));
                    }
                } catch (NumberFormatException ex) {
                    String message = "Invalid value for parameter: %s. Expected a number.".formatted(value);
                    throw new ApiRequestException(ApiError.BAD_REQUEST, "Invalid value", message);
                } catch (Exception ex) {
                    String message = "Error processing filter '%s': %s".formatted(key, ex.getMessage());
                    throw new ApiRequestException(ApiError.INTERNAL_SERVER_ERROR, "Internal server", message);
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}