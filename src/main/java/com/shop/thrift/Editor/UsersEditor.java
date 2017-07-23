//package com.shop.thrift.Editor;
//
//import com.shop.thrift.Entity.Users;
//import com.shop.thrift.Services.UsersService;
//
//import java.beans.PropertyEditorSupport;
//
//public class UsersEditor extends PropertyEditorSupport {
//
//    private final UsersService userService;
//
//    public UsersEditor(UsersService usersService){
//        this.userService = usersService;
//    }
//
//    @Override
//    public void setAsText(String text) throws IllegalArgumentException{
//        Users users = userService.findOne(Integer.valueOf(text));
//    }
//}
