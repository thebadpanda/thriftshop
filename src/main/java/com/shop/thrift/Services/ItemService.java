package com.shop.thrift.Services;

import com.shop.thrift.Entity.Item;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Filter.ItemFilter;
import com.shop.thrift.Form.ItemForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    void delete(int id);

    void save(ItemForm item);

    Item findOne(int id);

    Page<Item> findAll(ItemFilter filter, Pageable pageable);



}