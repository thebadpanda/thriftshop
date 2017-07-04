package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
}
