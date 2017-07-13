package com.shop.thrift.Services;

import com.shop.thrift.Entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    void delete(int id);

    void save(Item item);


}