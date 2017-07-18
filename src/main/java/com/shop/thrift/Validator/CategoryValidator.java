package com.shop.thrift.Validator;

import com.shop.thrift.Entity.Category;
import com.shop.thrift.Services.CategoryService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//import javax.validation.Validator;


public class CategoryValidator implements Validator {

    private final CategoryService categoryService;

    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Category.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if(categoryService.findOne(category.getName())!=null){
            errors.rejectValue("name", "", "Already exist");
        }
    }

}