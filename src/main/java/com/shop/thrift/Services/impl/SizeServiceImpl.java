package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Size;
import com.shop.thrift.Repository.SizeRepository;
import com.shop.thrift.Services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> findAll(){
        return sizeRepository.findAll();
    }



}
