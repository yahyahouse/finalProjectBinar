package com.binar.dummyproject.model.offer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferResponse {

    private Integer userId;
    private Long productId;
    private Long offerId;
    private Double offerPrice;
    private String offerStatus;
    private LocalDateTime localDateTime;

    public OfferResponse(Offer offer) {
        this.userId = offer.getUserId().getUserId();
        this.productId = offer.getProductId().getProductId();
        this.offerId = offer.getOfferId();
        this.offerPrice = offer.getOfferPrice();
        this.offerStatus = offer.getOfferStatus();
        this.localDateTime = offer.getLocalDateTime();
    }
}