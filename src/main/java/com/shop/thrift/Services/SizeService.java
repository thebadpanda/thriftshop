package com.shop.thrift.Services;


import com.shop.thrift.Entity.Size;
import com.shop.thrift.Filter.BasicFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SizeService {

    List<Size> findAll();

    Size findOne(int id);

    Size findOne(String name);

    Page<Size> findAll(BasicFilter filter,Pageable pageable);

    void delete(int id);

    void save(Size size);

}
