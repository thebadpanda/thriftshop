package com.shop.thrift.Specification;

import com.shop.thrift.Entity.Size;
import com.shop.thrift.dto.Filter.BasicFilter;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class SizeSpecification implements Specification<Size> {

    private final BasicFilter filter;

    public SizeSpecification(BasicFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Size> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(filter.getSearch().isEmpty()) return null;
        return cb.like(root.get("name"), filter.getSearch()+"%");
    }


}