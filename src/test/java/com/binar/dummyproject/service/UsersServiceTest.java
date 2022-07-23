package com.binar.dummyproject.service;

import com.binar.dummyproject.repository.users.UsersRepository;
import com.binar.dummyproject.service.users.UsersService;
import com.binar.dummyproject.service.users.UsersServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService = new UsersServiceImpl();


}
