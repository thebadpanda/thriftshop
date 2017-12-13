package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Entity.Item;
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


    // Item --> Basket
//    @Override
//    public List<Item> findByUsersId(int usersId) {
//        return basketRepository.findByUsersId(usersId);
//    }

    @Override
    public void delete(int id){
        basketRepository.delete(id);
    }

    @Override
    public void save(Basket basket){
        basketRepository.save(basket);
    }

    @Override
    public Basket findOne(int id){
       return basketRepository.findOne(id);
    }

//    @Override
//    public List<Basket> findBasketByUsers(int user_id) {
//        return basketRepository.findBasketByUsers(user_id);
//    }

    //    @Override
//    public void add(int id) {
//        basketRepository.add(id);
//    }
}
