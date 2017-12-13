package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SizeRepository extends JpaRepository<Size, Integer>, JpaSpecificationExecutor<Size> {
    //v1.Max
    @Query("SELECT s FROM Size s WHERE s.name = :name")
   // Size findBySizeName(@Param("name")String name);

    // v2.Sokol
    Size findBySizeName(String name);

    @Query("SELECT i FROM Size i LEFT JOIN FETCH i.items WHERE i.id = ?1")
    Size findOneByItem(int id);




}
