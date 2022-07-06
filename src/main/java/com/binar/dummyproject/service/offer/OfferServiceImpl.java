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
    public void saveOffer(Long offerId, Integer userId, Long productId, Double offerPrice, String offerStatus, LocalDateTime localDateTime) {
        Offer offer = new Offer();
        Users users = usersService.findByUserId(userId);
        Product product = productService.getProductById(productId);
        offer.setProductId(product);
        offer.setOfferId(offerId);
        offer.setUserId(users);
        offer.setOfferPrice(offerPrice);
        offer.setOfferStatus(offerStatus);
        offer.setLocalDateTime(localDateTime);
        offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOfferBySeller(Integer userId, Long productId) {
        return offerRepository.getOfferBySeller(userId, productId);
    }

    @Override
    public List<Offer> getOfferDetailByOfferId(Long offerId) {
        return offerRepository.getOfferDetailByOfferId(offerId);
    }

    @Override
    public List<Offer> getOfferByStatusDiminatiAndUserId(Long userId) {
        return offerRepository.getOfferByStatusDiminatiAndUserId(userId);
    }
}
