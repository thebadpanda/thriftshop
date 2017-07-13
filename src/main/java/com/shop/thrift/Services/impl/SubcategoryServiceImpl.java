package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Repository.CategoryRepository;
import com.shop.thrift.Repository.SubcategoryRepository;
import com.shop.thrift.Services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
