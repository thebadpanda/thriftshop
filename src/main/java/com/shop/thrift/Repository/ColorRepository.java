package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColorRepository extends JpaRepository<Color, Integer>, JpaSpecificationExecutor<Color> {
    @Query("SELECT cl FROM Color cl WHERE cl.name = :name")
    //Color findByColorName(@Param("name")String name);

   Color findByColorName(String name);
}
