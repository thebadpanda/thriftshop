package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Size;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Repository.SizeRepository;
import com.shop.thrift.Services.SizeService;
import com.shop.thrift.Specification.SizeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Size findOne(String name){
        return sizeRepository.findBySizeName(name);
    }

    @Override
    public void delete(int id){
        sizeRepository.delete(id);
    }

    @Override
    public void save(Size size){
        sizeRepository.save(size);
    }

    @Override
    public Size findOne(int id){
        return sizeRepository.findOne(id);
    }

    @Override
    public Page<Size> findAll(BasicFilter filter, Pageable pageable){
        return sizeRepository.findAll(new SizeSpecification(filter),pageable);
    }



}
