package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Color;
import com.shop.thrift.Repository.ColorRepository;
import com.shop.thrift.Services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> findAll(){
        return colorRepository.findAll();
    }

    @Override
    public Color findOne(int id){
        return colorRepository.findOne(id);
    }
 }
