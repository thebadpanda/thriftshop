package com.shop.thrift.Services;

import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Entity.Item;

import java.util.List;

public interface BasketService{

    List<Basket> findAll();

    void delete(int id);

    void save(Basket basket);

    Basket findOne(int id);

//    Item findByUsersId(int usersId);

//    List<Basket> findBasketByUsers(int user_id);

//    void add(int id);



}
