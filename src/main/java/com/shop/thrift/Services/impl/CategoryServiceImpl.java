package com.shop.thrift.Services.impl;
import com.shop.thrift.Entity.Category;
import com.shop.thrift.Repository.CategoryRepository;
import com.shop.thrift.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    public void delete(int id){
        categoryRepository.delete(id);
    }

    @Override
    public Category findOne(int id){
        return categoryRepository.findOne(id);
    }

    @Override
    public void save(Category category){
        categoryRepository.save(category);
    }

    @Override
    public Category category(int id){
        return categoryRepository.category(id);
    }

    @Override
    public Category findOne(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

}