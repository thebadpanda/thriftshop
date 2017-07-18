package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Users;
import com.shop.thrift.Repository.UsersRepository;
import com.shop.thrift.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    @Override
    public void delete(int id){
        usersRepository.delete(id);
    }

    @Override
    public void save(Users users){
        usersRepository.save(users);
    }

    @Override
    public Users findOne(int id){
        return usersRepository.findOne(id);
    }

//    @Override
//    public void update(Users users){
//        usersRepository.update(users);
//    }



}
