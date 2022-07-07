package com.binar.dummyproject.model.offer;

import com.binar.dummyproject.model.product.ProductImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OfferResponseNew {
    private Integer userId;
    private String username;
    private String city;
    private String phone;
    private List<String> url;
    private String productName;
    private Integer productPrice;
    private String productDescription;
    private String productCategory;
    private Long productId;
    private Long offerId;
    private Double offerPrice;
    private String offerStatus;
    private LocalDateTime localDateTime;

    public OfferResponseNew(Offer offer) {
        this.userId = offer.getUserId().getUserId();
        this.username = offer.getUserId().getUsername();
        this.city = offer.getUserId().getCity();
        this.phone = offer.getUserId().getPhone();
        this.url = offer.getProductId().getProductImages()
                .stream()
                .map(ProductImage::getUrl)
                .collect(Collectors.toList());
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
