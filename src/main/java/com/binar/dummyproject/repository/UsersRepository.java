package com.binar.dummyproject.repository;

import com.binar.dummyproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository <Users, Integer> {

    @Modifying
    @Query(value = "update users set username= :username, address= :address, phone= :phone " +
            "where user_id= :user_id", nativeQuery = true)
    Users updateUser(
            @Param("username") String username,
            @Param("address") String address,
            @Param("phone") String noHP,
            @Param("user_id") Integer userId
    );

    public Users findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
