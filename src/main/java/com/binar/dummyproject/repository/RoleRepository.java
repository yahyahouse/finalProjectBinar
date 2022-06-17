package com.binar.dummyproject.repository;

import com.binar.dummyproject.Enumeration.ERole;
import com.binar.dummyproject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(ERole name);
}
