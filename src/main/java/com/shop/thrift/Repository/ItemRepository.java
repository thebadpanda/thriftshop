package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
   @Query("SELECT i FROM Item i LEFT JOIN FETCH i.subcategory LEFT JOIN FETCH i.color LEFT JOIN FETCH i.size ")
   List<Item> findAll();
}
