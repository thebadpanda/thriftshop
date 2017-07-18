package com.shop.thrift.Validator;

import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Services.SubcategoryService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



public class SubcategoryValidator implements Validator{

    private final SubcategoryService subcategoryService;

    public SubcategoryValidator(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Subcategory.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Subcategory subcategory = (Subcategory) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if(subcategoryService.findOne(subcategory.getName())!=null){
            errors.rejectValue("name", "", "Already exist");
        }
    }
}
