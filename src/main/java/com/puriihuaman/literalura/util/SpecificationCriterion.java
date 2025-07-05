package com.puriihuaman.literalura.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@FunctionalInterface
public interface SpecificationCriterion<T> {
    Predicate toPredicate(Root<T> root, CriteriaBuilder cb, String value);
}