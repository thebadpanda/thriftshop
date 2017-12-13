package com.shop.thrift.Services;

import com.shop.thrift.Entity.Users;
import com.shop.thrift.dto.Filter.BasicFilter;
import com.shop.thrift.dto.Filter.ItemFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {


    void save(Users users);

    int createNewUser();

    void addToBasket(int usersId, int itemId);

    void removeToBasket(int usersId, int itemId);

    void removeAllToBasket(int usersId);

    void sendMail(String content, String email, String mailBody);

    Users findByEmail(String email);

    List<Users> findAll(BasicFilter filter, Pageable pageable);

    void delete(int id);

    Users findOne(int id);

    Users findOne(String username);

    //Page<Users> findAll(BasicFilter filter, Pageable pageable);

   // void update(Users users);





}
