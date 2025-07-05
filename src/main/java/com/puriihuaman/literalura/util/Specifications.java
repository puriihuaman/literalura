package com.puriihuaman.literalura.util;

import com.puriihuaman.literalura.persistence.domain.AuthorEntity;
import com.puriihuaman.literalura.persistence.domain.BookEntity;
import com.puriihuaman.literalura.persistence.domain.TranslatorEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Specifications {
    private Specifications() {
    }
    
    public static Specification<BookEntity> forBook(Map<String, String> keywords) {
        Map<String, SpecificationCriterion<BookEntity>> bookCriteria = new ConcurrentHashMap<>();
        
        bookCriteria.put("title", (root, cb, value) -> cb.like(cb.lower(root.get("title")), buildString(value)));
        
        bookCriteria.put("summary", (root, cb, value) -> cb.like(cb.lower(root.get("summary")), buildString(value)));
        
        bookCriteria.put("copyright", (root, cb, value) -> cb.equal(root.get("copyright"), buildString(value)));
        
        bookCriteria.put(
                "media-type",
                (root, cb, value) -> cb.equal(cb.lower(root.get("mediaType")), value)
        );
        
        bookCriteria.put("max-download", (root, cb, value) -> cb.greaterThanOrEqualTo(root.get("downloadCount"), value));
        
        bookCriteria.put("min-download", (root, cb, value) -> cb.lessThanOrEqualTo(root.get("downloadCount"), value));
        
        bookCriteria.put("language", (root, cb, value) -> cb.equal(root.join("languages").get("code"), value));
        
        
        return new GenericSpecificationBuilder<>(bookCriteria).buildSpecification(keywords);
    }
    
    private static <E> Specification<E> forPerson(Map<String, String> keywords) {
        Map<String, SpecificationCriterion<E>> personCriteria = new ConcurrentHashMap<>();
        
        personCriteria.put("name", (root, cb, value) -> cb.like(cb.lower(root.get("name")), buildString(value)));
        
        personCriteria.put("birth-year", (root, cb, value) -> cb.equal(root.get("birthYear"), value));
        
        personCriteria.put("death-year", (root, cb, value) -> cb.equal(root.get("deathYear"), value));
        
        return new GenericSpecificationBuilder<>(personCriteria).buildSpecification(keywords);
    }
    
    public static Specification<AuthorEntity> forAuthor(Map<String, String> keywords) {
        return forPerson(keywords);
    }
    
    public static Specification<TranslatorEntity> forTranslator(Map<String, String> keywords) {
        return forPerson(keywords);
    }
    
    private static String buildString(final String value) {
        return "%" + value.trim().toLowerCase() + "%";
    }
}