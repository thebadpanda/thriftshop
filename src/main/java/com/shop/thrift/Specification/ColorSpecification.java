package com.shop.thrift.Specification;

import com.shop.thrift.Entity.Color;
import com.shop.thrift.Filter.BasicFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ColorSpecification implements Specification<Color> {

    private final BasicFilter filter;

    public ColorSpecification(BasicFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Color> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(filter.getSearch().isEmpty()) return null;
        return cb.like(root.get("name"), filter.getSearch()+"%");
    }


}