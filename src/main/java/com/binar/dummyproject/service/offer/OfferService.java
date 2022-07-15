package com.binar.dummyproject.service.offer;

import com.binar.dummyproject.model.offer.Offer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface OfferService {

    List<Offer> getAllOfferByUser(Integer userId);
    void saveOffer(Integer userId, Long productId, Double offerPrice,
                   String offerStatus, LocalDateTime localDateTime);
    List<Offer> getOfferBySeller(Integer userId);

    List<Offer> getOfferByStatusDiminati(Integer userId);

    List<Offer> getOfferByOfferStatusAndProductSold(Integer userId);

    void acceptedStatus(Long offerId);
    Offer findOfferById(Long offerId);
    void rejectedStatus(Long offerId);

    List<Offer> getOfferDiminati();

}
