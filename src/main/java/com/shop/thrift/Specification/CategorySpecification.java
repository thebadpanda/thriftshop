package com.shop.thrift.Specification;


import com.shop.thrift.Entity.Category;
import com.shop.thrift.dto.Filter.BasicFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification implements Specification<Category> {

    private final BasicFilter filter;

    public CategorySpecification(BasicFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(filter.getSearch().isEmpty()) return null;
        return cb.like(root.get("name"), filter.getSearch()+"%");
    }
}