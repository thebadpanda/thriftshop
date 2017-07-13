package com.shop.thrift.Services;

import com.shop.thrift.Entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    void delete (int id);
    Category findOne(int id);
    void save(Category category);
    Category category (int id);



}
