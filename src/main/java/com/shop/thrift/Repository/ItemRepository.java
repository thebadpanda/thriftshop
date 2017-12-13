package com.shop.thrift.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.shop.thrift.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item>{
   @Query("SELECT i FROM Item i LEFT JOIN FETCH i.subcategory LEFT JOIN FETCH i.color LEFT JOIN FETCH i.size ")
   List<Item> findAll();

   @Query(value="SELECT i FROM Item i LEFT JOIN FETCH i.subcategory LEFT JOIN FETCH i.color LEFT JOIN FETCH i.size ",
           countQuery="SELECT count(i.id) FROM Item i")

   Page<Item> findAll(Pageable pageable);

   @Query("SELECT b.count FROM Users u JOIN u.basket b WHERE u.id=?1")
   Integer findCount(int id);

   @Query("SELECT i FROM Item i JOIN i.basket b JOIN b.users u WHERE u.id=?1")
   List<Item> findByUsersId(int usersId);

   @Query("SELECT i FROM Item i JOIN i.subcategory sc WHERE sc.id = ?1")
   List<Item> findBySubcategoryId(int id);
}
