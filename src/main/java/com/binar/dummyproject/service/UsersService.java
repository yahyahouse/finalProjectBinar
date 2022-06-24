package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Users;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Optional;

@Service
public interface UsersService {
    public void updateUsersProfile (Integer userId, String username, String address, String phone, String city, String usersImage);
    public Users getByUsername(String username);
    Optional<Users> getUser(SingularAttribute<AbstractPersistable, Serializable> id);
    public void updateUsersPassword(String password, Integer userId);
}
