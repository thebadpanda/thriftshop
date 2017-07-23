package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Repository.CategoryRepository;
import com.shop.thrift.Repository.SubcategoryRepository;
import com.shop.thrift.Services.SubcategoryService;
import com.shop.thrift.Specification.SubcategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Subcategory> findAll(){
        return subcategoryRepository.findAll();
    }

    @Override
    public void delete(int id){
        subcategoryRepository.delete(id);
    }

    @Override
    public void save(Subcategory subcategory){
        subcategoryRepository.save(subcategory);
    }

    @Override
    public Subcategory findOne(String name){
       return subcategoryRepository.findByName(name);
    }

    @Override
    public Subcategory findOne(int id){
        return subcategoryRepository.findOne(id);
    }

    @Override
    public Page<Subcategory> findAll(BasicFilter filter, Pageable pageable){
        return subcategoryRepository.findAll(new SubcategorySpecification(filter),pageable);
    }
}
