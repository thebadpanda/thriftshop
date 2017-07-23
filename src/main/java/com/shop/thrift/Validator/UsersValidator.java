package com.shop.thrift.Validator;

import com.shop.thrift.Entity.Users;
import com.shop.thrift.Services.UsersService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class UsersValidator implements Validator {

    private final static Pattern REG = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private final static Pattern REG1 = Pattern.compile("^{8,20}$");

    private final UsersService usersService;

    public UsersValidator(UsersService userService) {
        this.usersService = userService;
    }

//	^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users users = (Users) target;
        if(!REG.matcher(users.getEmail()).matches() & !REG1.matcher(users.getPassword()).matches()){
            errors.rejectValue("email", "", "This is not email");
            errors.rejectValue("password", "", "The password must contain at least 8 to 20 characters");
        }
        if(errors.getFieldError("email")==null & (errors.getFieldError("username")==null) & (errors.getFieldError("password")==null)){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can't be empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can't be empty");
            if(usersService.findByEmail(users.getEmail())!=null){
                errors.rejectValue("email", "", "Already exist, please try another");
            }
        }
    }
}

//    private final UsersService usersService;
//
//    public UsersValidator(UsersService usersService){
//        this.usersService = usersService;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz){
//       return clazz.equals(Users.class);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors){
//        Users users = (Users) target;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Can`t be empty");
//        if(usersService.findOne(users.getLogin())!=null){
//            errors.rejectValue("login", "", "Already exist");
//        }
//    }

