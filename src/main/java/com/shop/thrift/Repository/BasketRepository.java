package com.shop.thrift.Repository;


import com.shop.thrift.Entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer>{

}
