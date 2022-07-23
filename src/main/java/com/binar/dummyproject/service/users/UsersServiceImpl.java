package com.binar.dummyproject.service.users;

import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsersServiceImpl() {

    }

    @Override
    public void updateUsersProfile(Integer userId, String fullNameUser, String address, String phone, String city) {
        usersRepository.updateUser(fullNameUser, address, phone, city, userId);
    }

    @Override
    public Users getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> getUser(SingularAttribute<AbstractPersistable, Serializable> id) {
        return Optional.empty();
    }

    @Override
    public void updateUsersPassword(String password, Integer userId) {
        usersRepository.updatePassword(passwordEncoder.encode(password), userId);
    }

    @Override
    public List<Users> getUsersByUserId(Integer userId) {
        return usersRepository.findUsersByUserId(userId);
    }

    @Override
    public Users findByUserId(Integer userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
