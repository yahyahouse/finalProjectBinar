package com.binar.dummyproject.service.users;

import com.binar.dummyproject.model.Users;
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

    @Override
    public void updateUsersProfile(Integer userId, String username, String address, String phone, String city, String usersImage) {
        usersRepository.updateUser(username, address, phone, city, usersImage, userId);
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

}
