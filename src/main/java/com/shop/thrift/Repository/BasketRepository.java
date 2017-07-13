package com.shop.thrift.Repository;


import com.shop.thrift.Entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket, Integer>{

    @Query("SELECT b FROM Basket b LEFT JOIN FETCH b.users WHERE b.id = :id")
    Basket basket(@Param("id")int id);

}
