package com.binar.dummyproject.repository;

import com.binar.dummyproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
