package com.shop.thrift.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shop.thrift.Entity.Item;
import com.shop.thrift.Filter.ItemFilter;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification implements Specification<Item>{

    private final ItemFilter filter;

    private final List<Predicate> predicates = new ArrayList<>();

    public ItemSpecification(ItemFilter filter) {
        this.filter = filter;
    }

    private void filterByColor(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(!filter.getColorIds().isEmpty()){
            predicates.add(root.get("color").in(filter.getColorIds()));
        }
    }

    private void filterBySize(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(!filter.getSizeIds().isEmpty()){
            predicates.add(root.get("size").in(filter.getSizeIds()));
        }
    }

    private void filterByPrice(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
        if(filter.getMax()!=null&&filter.getMin()!=null){
            predicates.add(cb.between(root.get("price"), filter.getMin(), filter.getMax()));
        }else if(filter.getMax()!=null){
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMax()));
        }else if(filter.getMin()!=null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMin()));
        }
    }

    private void filterBySearch(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
        if(!filter.getSearch().isEmpty()){
            predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
        }
    }

    private void fetch(Root<Item> root, CriteriaQuery<?> query){
        if(query.getResultType()!=Long.class){
            root.fetch("subcategory", JoinType.LEFT);
            root.fetch("size", JoinType.LEFT);
            root.fetch("color", JoinType.LEFT);
        }
    }

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        fetch(root, query);
        query.distinct(true);
        filterBySearch(root, query, cb);
        filterByPrice(root, query, cb);
        filterByColor(root, query, cb);
        filterBySize(root, query, cb);
        if(predicates.isEmpty())return null;
        Predicate[] array = new Predicate[predicates.size()];
        predicates.toArray(array);
        return cb.and(array);
    }

//    private class DigitSpecification implements Specification<Item>{
//
//        private final SpecDigitFilter digitFilter;
//
//        private final List<Predicate> predicatesDigit = new ArrayList<>();
//
//        public DigitSpecification(SpecDigitFilter digitFilter) {
//            this.digitFilter = digitFilter;
//        }
//
//        private void filterByValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//            if(digitFilter.getMaxValue()!=null&&digitFilter.getMinValue()!=null){
//                filterByMinMaxValue(root, query, cb);
//            }else if(digitFilter.getMaxValue()!=null){
//                filterByMaxValue(root, query, cb);
//            }else if(digitFilter.getMinValue()!=null){
//                filterByMinValue(root, query, cb);
//            }
//        }
//
//        private void filterByMinValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//            filterByNameId(root, query, cb);
//            filterByMsId(root, query, cb);
//            Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
//            predicatesDigit.add(cb.greaterThanOrEqualTo(join.get("value"), digitFilter.getMinValue()));
//        }
//
//        private void filterByMaxValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//            filterByNameId(root, query, cb);
//            filterByMsId(root, query, cb);
//            Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
//            predicatesDigit.add(cb.lessThanOrEqualTo(join.get("value"), digitFilter.getMaxValue()));
//        }
//
//        private void filterByMinMaxValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//            filterByNameId(root, query, cb);
//            filterByMsId(root, query, cb);
//            Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
//            predicatesDigit.add(cb.between(join.get("value"), digitFilter.getMinValue(), digitFilter.getMaxValue()));
//        }
//
//        private void filterByMsId(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//            if(digitFilter.getMsId()!=null){
//                Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
//                Join<SpecificationDigital, MeasuringSystem> joinMs = join.join("measuringSystems");
//                predicatesDigit.add(cb.equal(joinMs.get("id"), digitFilter.getMsId()));
//            }
//        }
//
//        private void filterByNameId(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//            if(digitFilter.getNameId()!=null){
//                Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
//                predicatesDigit.add(cb.equal(join.get("nameOfSpecificationDigital"), digitFilter.getNameId()));
//            }
//        }
//
//        @Override
//        public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//            filterByValue(root, query, cb);
//            if(predicatesDigit.isEmpty())return null;
//            filterByMsId(root, query, cb);
//            filterByNameId(root, query, cb);
//            Predicate[] array = new Predicate[predicatesDigit.size()];
//            predicatesDigit.toArray(array);
//            return cb.and(array);
//        }
//    }
}