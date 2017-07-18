package com.shop.thrift.Editor;

import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Services.SubcategoryService;

import java.beans.PropertyEditorSupport;

public class SubcategoryEditor extends PropertyEditorSupport {

    private final SubcategoryService subcategoryService;

    public SubcategoryEditor(SubcategoryService subcategoryService){
        this.subcategoryService = subcategoryService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Subcategory subcategory = subcategoryService.findOne(Integer.valueOf(text));
    }
}
