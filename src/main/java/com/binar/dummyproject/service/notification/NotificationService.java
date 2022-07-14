package com.binar.dummyproject.service.notification;

import com.binar.dummyproject.model.notification.Notification;
import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    void saveNotification (Offer offer, Product product, Integer userId);

    void saveNotification(Product product, Integer userId);

    void updateIsRead(Long notifId);

    List<Notification> getNotification(Integer userId);
}
