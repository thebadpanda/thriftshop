package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
