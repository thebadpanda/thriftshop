package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Item;
import com.shop.thrift.Repository.ColorRepository;
import com.shop.thrift.Repository.ItemRepository;
import com.shop.thrift.Repository.SizeRepository;
import com.shop.thrift.Repository.SubcategoryRepository;
import com.shop.thrift.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    @Override
    public void delete(int id){
        itemRepository.delete(id);
    }

    @Override
    public void save(Item item){
        itemRepository.save(item);
    }


}
