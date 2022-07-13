package com.binar.dummyproject.service.offer;

import com.binar.dummyproject.model.offer.Offer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface OfferService {

    List<Offer> getAllOfferByUser(Integer userId);
    void saveOffer(Integer userId, Long productId, Double offerPrice,
                   String offerStatus, LocalDateTime localDateTime);
    List<Offer> getOfferBySeller(Integer userId, Long productId);

    List<Offer> getOfferByStatusDiminati(Long offerId);

    List<Offer> getOfferByOfferStatusAndProductSold(Integer userId);

    void acceptedStatus(Long offerId);
    Optional<Offer> findOfferById(Long offerId);
    void rejectedStatus(Long offerId);



}
