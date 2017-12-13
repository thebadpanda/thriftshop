//package com.shop.thrift.Specification;
//
//import com.shop.thrift.Entity.Users;
//import com.shop.thrift.dto.Filter.BasicFilter;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//public class UsersSpecification implements Specification<Users> {
//
//    private final BasicFilter filter;
//
//    public UsersSpecification(BasicFilter filter) {
//        this.filter = filter;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        if(filter.getSearch().isEmpty()) return null;
//        return cb.like(root.get("name"), filter.getSearch()+"%");
//    }
//}
