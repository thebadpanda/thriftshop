package com.shop.thrift.Services;


import com.shop.thrift.Entity.Size;

import java.util.List;

public interface SizeService {

    List<Size> findAll();

}
