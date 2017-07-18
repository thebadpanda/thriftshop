package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Item;
import com.shop.thrift.Form.ItemForm;
import com.shop.thrift.Repository.ColorRepository;
import com.shop.thrift.Repository.ItemRepository;
import com.shop.thrift.Repository.SizeRepository;
import com.shop.thrift.Repository.SubcategoryRepository;
import com.shop.thrift.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
    public void save(ItemForm itemForm){
        Item item = new Item();
        item.setId(itemForm.getId());
        item.setSubcategory(itemForm.getSubcategory());
        item.setName(itemForm.getName());
        item.setPrice(new BigDecimal(itemForm.getPrice().replace(',', '.')));
        item.setColor(itemForm.getColor());
        item.setSize(itemForm.getSize());
        itemRepository.save(item);

    }

    @Override
    public Item findOne(int id){
        return itemRepository.findOne(id);
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }


}
