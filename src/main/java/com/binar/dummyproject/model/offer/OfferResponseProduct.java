package com.binar.dummyproject.model.offer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferResponseProduct {
    private Integer userId;
    private Long productId;
    private Long offerId;
    private Double offerPrice;
    private String offerStatus;
    private LocalDateTime localDateTime;
    private String productCategory;
    private String productDescription;
    private String productName;
    private Integer productPrice;
    private String productStatus;
    private String url;
    private String url2;
    private String url3;
    private String url4;

    public OfferResponseProduct(Offer offer) {
        this.userId = offer.getUserId().getUserId();
        this.productId = offer.getProductId().getProductId();
        this.url = offer.getProductId().getUrl();
        this.url2 = offer.getProductId().getUrl2();
        this.url3 = offer.getProductId().getUrl3();
        this.url4 = offer.getProductId().getUrl4();
        this.productName = offer.getProductId().getProductName();
        this.productPrice = offer.getProductId().getProductPrice();
        this.productDescription = offer.getProductId().getProductDescription();
        this.productCategory = offer.getProductId().getProductCategory();
        this.productStatus = offer.getProductId().getProductStatus();
        this.offerId = offer.getOfferId();
        this.offerPrice = offer.getOfferPrice();
        this.offerStatus = offer.getOfferStatus();
        this.localDateTime = offer.getLocalDateTime();
    }
}
