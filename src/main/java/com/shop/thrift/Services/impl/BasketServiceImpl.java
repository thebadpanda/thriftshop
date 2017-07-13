package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Repository.BasketRepository;
import com.shop.thrift.Services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class BasketServiceImpl implements BasketService{

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<Basket> findAll(){
        return basketRepository.findAll();
    }

    @Override
    public void delete(int id){
        basketRepository.delete(id);
    }

    @Override
    public void save(Basket basket){
        basketRepository.save(basket);
    }



}
