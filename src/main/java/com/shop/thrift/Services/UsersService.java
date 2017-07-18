package com.shop.thrift.Services;

import com.shop.thrift.Entity.Users;

import java.util.List;

public interface UsersService {

    List<Users> findAll();

    void delete(int id);

    void save(Users users);

    Users findOne(int id);

   // void update(Users users);



}
