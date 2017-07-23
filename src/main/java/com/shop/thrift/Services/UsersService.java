package com.shop.thrift.Services;

import com.shop.thrift.Entity.Users;
import com.shop.thrift.Filter.BasicFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {


    void save(Users users);

    List<Users> findAll();

    void delete(int id);

    Users findOne(int id);

    Users findOne(String username);

    Page<Users> findAll(BasicFilter filter, Pageable pageable);

   // void update(Users users);

    Users findByEmail(String email);



}
