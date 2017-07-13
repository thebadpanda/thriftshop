package com.shop.thrift.Services;

import com.shop.thrift.Entity.Color;

import java.util.List;

public interface ColorService {

    List<Color> findAll();

}
