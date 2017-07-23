package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Role;
import com.shop.thrift.Entity.Users;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Repository.UsersRepository;
import com.shop.thrift.Services.UsersService;
import com.shop.thrift.Specification.UsersSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("userDetailsService")
public class UsersServiceImpl implements UserDetailsService, UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }

    @Override
    public void save(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRole(Role.ROLE_USER);
        usersRepository.save(users);
    }

    @PostConstruct
    public void admin() {
        Users users = usersRepository.findByUsername("admin");
        if (users == null) {
            users = new Users();
            users.setEmail("");
            users.setPassword(encoder.encode("admin"));
            users.setRole(Role.ROLE_ADMIN);
            users.setUsername("admin");
            usersRepository.save(users);
        }
    }
        @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public void delete(int id) {
        usersRepository.delete(id);
    }

    @Override
    public Users findOne(int id){
        return usersRepository.findOne(id);
    }

    @Override
    public Users findOne(String username){
        return usersRepository.findByUsername(username);
    }

    @Override
    public Page<Users> findAll(BasicFilter filter, Pageable pageable){
        return usersRepository.findAll(new UsersSpecification(filter), pageable);
    }

    @Override
    public Users findByEmail(String email){
        return usersRepository.findByEmail(email);


//    @Override
//    public void update(Users users){
//        usersRepository.update(users);
//    }


    }
}
