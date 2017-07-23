package com.shop.thrift.Services;

import com.shop.thrift.Entity.Color;
import com.shop.thrift.Filter.BasicFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService {

    List<Color> findAll();

    Color findOne(int id);

    Color findOne(String name);

    Page<Color> findAll(BasicFilter filter, Pageable pageable);

    void delete(int id);

    void save(Color color);

}
