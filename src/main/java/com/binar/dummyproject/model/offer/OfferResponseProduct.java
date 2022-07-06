package com.binar.dummyproject.model.offer;

import com.binar.dummyproject.model.product.ProductImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> url;

    public OfferResponseProduct(Offer offer) {
        this.userId = offer.getUserId().getUserId();
        this.productId = offer.getProductId().getProductId();
        this.url = offer.getProductId().getProductImages()
                .stream()
                .map(ProductImage::getUrl)
                .collect(Collectors.toList());
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
