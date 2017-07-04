package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer>{
}
