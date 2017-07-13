package com.shop.thrift.Services;

import com.shop.thrift.Entity.Basket;

import java.util.List;

public interface BasketService{

    List<Basket> findAll();

    void delete(int id);

    void save(Basket basket);



}
