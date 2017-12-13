package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Color;
import com.shop.thrift.dto.Filter.BasicFilter;
import com.shop.thrift.Repository.ColorRepository;
import com.shop.thrift.Services.ColorService;
import com.shop.thrift.Specification.ColorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Color findOne(String name){
        return colorRepository.findByColorName(name);
    }

    @Override
    public void delete(int id){
        colorRepository.delete(id);
    }

    @Override
    public void save(Color color){
        colorRepository.save(color);
    }

    @Override
    public Page<Color> findAll(BasicFilter filter, Pageable pageable){
        return colorRepository.findAll(new ColorSpecification(filter),pageable);
    }

    @Override
    public Color findOneByItem(int id) {
        return colorRepository.findOneByItem(id);
    }

 }
