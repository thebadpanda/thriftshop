package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightRepository extends JpaRepository<Weight, Integer> {
}
