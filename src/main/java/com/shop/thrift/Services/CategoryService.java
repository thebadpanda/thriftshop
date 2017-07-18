package com.shop.thrift.Services;

import com.shop.thrift.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    void delete (int id);
    Category findOne(int id);
    void save(Category category);
    Category category (int id);
    Category findOne(String name);
    Page<Category> findAll(Pageable pageable);




}
