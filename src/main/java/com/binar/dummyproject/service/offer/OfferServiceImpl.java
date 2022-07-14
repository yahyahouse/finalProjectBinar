package com.binar.dummyproject.service.offer;

import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.offer.OfferRepository;
import com.binar.dummyproject.service.product.ProductService;
import com.binar.dummyproject.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Offer> getAllOfferByUser(Integer userId) {
        return offerRepository.getAllByUserId(userId);
    }

    @Override
    public void saveOffer(Integer userId, Long productId, Double offerPrice, String offerStatus, LocalDateTime localDateTime) {
        Offer offer = new Offer();
        Users users = usersService.findByUserId(userId);
        Product product = productService.getProductById(productId);
        offer.setProductId(product);
        offer.setUserId(users);
        offer.setOfferPrice(offerPrice);
        offer.setOfferStatus(offerStatus);
        offer.setLocalDateTime(localDateTime);
        offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOfferBySeller(Integer userId) {
        return offerRepository.getOfferBySeller(userId);
    }

    @Override
    public List<Offer> getOfferByStatusDiminati(Long offerId) {
        return offerRepository.getOfferByStatusDiminati(offerId);
    }

    @Override
    public List<Offer> getOfferByOfferStatusAndProductSold(Integer userId) {
        return offerRepository.getOfferByOfferStatusAndProductSold(userId);
    }

    @Override
    public void acceptedStatus(Long offerId) {
        offerRepository.statusAccepted(offerId);
    }

    @Override
    public Optional<Offer> findOfferById(Long offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public void rejectedStatus(Long offerId) {
        offerRepository.statusRejected(offerId);
    }


}