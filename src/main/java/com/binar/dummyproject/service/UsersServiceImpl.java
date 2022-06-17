package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> getUser(SingularAttribute<AbstractPersistable, Serializable> id) {
        return Optional.empty();
    }
}
