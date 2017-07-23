package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.subcategories WHERE c.id = :id")
    Category category(@Param("id")int id);

    Category findByName(String name);
}
