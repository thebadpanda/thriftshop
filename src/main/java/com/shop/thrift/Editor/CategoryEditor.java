package com.shop.thrift.Editor;

import com.shop.thrift.Entity.Category;
import com.shop.thrift.Services.CategoryService;

import java.beans.PropertyEditorSupport;

public class CategoryEditor extends PropertyEditorSupport{

    private final CategoryService categoryService;

    public CategoryEditor(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Category category = categoryService.findOne(Integer.valueOf(text));
        setValue(category);
    }

}
