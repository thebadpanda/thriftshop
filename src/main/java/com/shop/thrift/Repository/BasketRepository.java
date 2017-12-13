package com.shop.thrift.Repository;


import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer>{

    @Query("SELECT b FROM Basket b LEFT JOIN FETCH b.users WHERE b.id = :id")
    Basket basket(@Param("id")int id);

//    @Query("SELECT i FROM Item i JOIN i.basket b JOIN b.users u WHERE u.id=?1")
//    List<Item> findByUsersId(int usersId);

//    @Query("SELECT b FROM Basket b JOIN b.item i JOIN b.users u WHERE u.id=?1")
//    List<Basket> findBasketByUsers(int usersId);
}
