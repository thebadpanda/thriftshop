package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer>, JpaSpecificationExecutor<Subcategory>{
    @Query("SELECT sc FROM Subcategory sc LEFT JOIN FETCH sc.category")
    List<Subcategory> findAll();


    //АРСЕН ТУТ МОЖЕ БУТИ ПОМИЛКА -макс
    @Query("SELECT sc FROM Subcategory sc LEFT JOIN FETCH sc.category WHERE sc.id=:id")
    Subcategory subcategory (@Param("id")int id);

    Subcategory findByName(String name);
}
