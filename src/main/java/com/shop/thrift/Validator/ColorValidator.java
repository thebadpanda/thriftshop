package com.shop.thrift.Validator;

import com.shop.thrift.Entity.Color;
import com.shop.thrift.Services.ColorService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class ColorValidator implements Validator {

    private final ColorService colorService;

    public ColorValidator(ColorService colorService) {
        this.colorService = colorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Color.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Color color = (Color)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if(colorService.findOne(color.getId())!=null){
            errors.rejectValue("name", "", "Already exist");
        }

    }


}