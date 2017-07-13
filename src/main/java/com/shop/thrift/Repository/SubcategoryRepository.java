package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer>{
    @Query("SELECT sc FROM Subcategory sc LEFT JOIN FETCH sc.category")
    List<Subcategory> findAll();


    //АРСЕН ТУТ МОЖЕ БУТИ ПОМИЛКА -макс
    @Query("SELECT sc FROM Subcategory sc LEFT JOIN FETCH sc.category WHERE sc.id=:id")
    Subcategory subCat(@Param("id")int id);
}
