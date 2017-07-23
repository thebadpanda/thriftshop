package com.shop.thrift.Services;

import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Filter.BasicFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubcategoryService {

    List<Subcategory> findAll();

    void delete (int id);

    void save (Subcategory subcategory);

    Subcategory findOne(String name);

    Subcategory findOne(int id);

    Page<Subcategory> findAll(BasicFilter filter, Pageable pageable);




}
