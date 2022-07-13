package com.binar.dummyproject.model.offer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferResponseNew {
    private Integer userId;
    private String username;
    private String city;
    private String phone;
    private String productName;
    private Integer productPrice;
    private String productDescription;
    private String productCategory;
    private Long productId;
    private Long offerId;
    private Double offerPrice;
    private String offerStatus;
    private LocalDateTime localDateTime;
    private String url;
    private String url2;
    private String url3;
    private String url4;

    public OfferResponseNew(Offer offer) {
        this.userId = offer.getUserId().getUserId();
        this.username = offer.getUserId().getUsername();
        this.city = offer.getUserId().getCity();
        this.phone = offer.getUserId().getPhone();
        this.url = offer.getProductId().getUrl();
        this.url2 = offer.getProductId().getUrl2();
        this.url3 = offer.getProductId().getUrl3();
        this.url4 = offer.getProductId().getUrl4();
        this.productName = offer.getProductId().getProductName();
        this.productPrice = offer.getProductId().getProductPrice();
        this.productDescription = offer.getProductId().getProductDescription();
        this.productCategory = offer.getProductId().getProductCategory();
        this.productId = offer.getProductId().getProductId();
        this.offerId = offer.getOfferId();
        this.offerPrice = offer.getOfferPrice();
        this.offerStatus = offer.getOfferStatus();
        this.localDateTime = offer.getLocalDateTime();

    }
}
