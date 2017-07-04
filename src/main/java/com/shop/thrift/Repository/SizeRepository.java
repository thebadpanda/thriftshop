package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Integer> {
}
