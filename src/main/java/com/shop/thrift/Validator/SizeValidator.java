package com.shop.thrift.Validator;


import com.shop.thrift.Entity.Size;
import com.shop.thrift.Services.SizeService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SizeValidator implements Validator {

    private final SizeService sizeService;

    public SizeValidator(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Size.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Size size = (Size)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if(sizeService.findOne(size.getId())!=null){
            errors.rejectValue("name", "", "Already exist");
        }

    }


}
