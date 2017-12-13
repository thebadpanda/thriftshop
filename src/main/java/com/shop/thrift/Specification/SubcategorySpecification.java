package com.shop.thrift.Specification;

import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.dto.Filter.BasicFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SubcategorySpecification implements Specification<Subcategory> {

    private final BasicFilter filter;

    public SubcategorySpecification(BasicFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Subcategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(filter.getSearch().isEmpty()) return null;
        return cb.like(root.get("name"), filter.getSearch()+"%");
    }
}