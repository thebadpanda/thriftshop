package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface WeightRepository extends JpaRepository<Weight, Integer> {

}
