package com.binar.dummyproject.service.notification;

import com.binar.dummyproject.model.notification.Notification;
import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.notification.NotificationRepository;
import com.binar.dummyproject.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UsersService usersService;

    @Override
    public void saveNotification(Offer offer, Product product, Integer userId) {
        Notification notification = new Notification();
        Users users = usersService.findByUserId(userId);
        notification.setOfferId(offer);
        notification.setUserId(users);
        notification.setProductId(product);
        notificationRepository.save(notification);
    }

    @Override
    public void saveNotification(Product product, Integer userId) {
        Notification notification = new Notification();
        Users users = usersService.findByUserId(userId);
        notification.setProductId(product);
        notification.setUserId(users);
        notificationRepository.save(notification);
    }

    @Override
    public void updateIsRead(Long notifId) {
        Optional<Notification> notification = notificationRepository.findByNotifId(notifId);
        notification.ifPresent(notification1 -> {
            notification1.setIsRead(true);
            notificationRepository.save(notification1);
        });
    }

    @Override
    public List<Notification> getNotification(Integer userId) {
        return notificationRepository.findNotif(userId);
    }
}
