package com.binar.dummyproject.model.notification;

import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.product.Product;
import lombok.Data;

@Data
public class NotificationResponse {

    private Long notifId;
    private Boolean isRead;
    private Double offerPrice;
    private String productName;
    private Integer productPrice;
    private String url;
    private Integer userId;

    public NotificationResponse() {

    }

    public NotificationResponse(Notification notification, Product product, Offer offer){
        this.notifId = notification.getNotifId();
        this.isRead = notification.getIsRead();
        this.offerPrice = offer.getOfferPrice();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.url = product.getUrl();
        this.userId = notification.getUserId().getUserId();
    }

    public NotificationResponse(Notification notification, Product product) {
        this.notifId = notification.getNotifId();
        this.isRead = notification.getIsRead();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.url = product.getUrl();
        this.userId = notification.getUserId().getUserId();
    }
}
