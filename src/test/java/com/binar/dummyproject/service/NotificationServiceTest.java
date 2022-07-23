package com.binar.dummyproject.service;


import com.binar.dummyproject.model.notification.Notification;
import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.notification.NotificationRepository;
import com.binar.dummyproject.service.notification.NotificationService;
import com.binar.dummyproject.service.notification.NotificationServiceImpl;
import com.binar.dummyproject.service.product.ProductService;
import com.binar.dummyproject.service.users.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    private UsersService usersService;

    private ProductService productService;

    @InjectMocks
    private NotificationService notificationService = new NotificationServiceImpl();

    @Test
    @DisplayName("Get notification")
    void getNotification(){
        Integer userId = 1;
        List<Notification> notifications = new ArrayList<>();
        when(notificationRepository.findNotif(userId)).thenReturn(notifications);
        assertSame(notifications, notificationService.getNotification(userId));
    }

}