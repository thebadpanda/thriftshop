package com.shop.thrift.Repository;

import com.shop.thrift.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.basket WHERE u.id = :id")
    Users users (@Param("id")int id);

    Users findByUsername(String username);

    Users findByEmail(String email);

}
