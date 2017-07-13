package com.shop.thrift.Services;

import com.shop.thrift.Entity.Subcategory;

import java.util.List;

public interface SubcategoryService {

    List<Subcategory> findAll();

    void delete (int id);

    void save (Subcategory subcategory);




}
