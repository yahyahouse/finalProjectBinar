package com.binar.dummyproject.service.offer;

import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.offer.OfferRepository;
import com.binar.dummyproject.service.notification.NotificationService;
import com.binar.dummyproject.service.product.ProductService;
import com.binar.dummyproject.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.binar.dummyproject.model.InfoConst.*;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProductService productService;

    @Autowired
    private NotificationService notificationService;

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

        Integer sellerId = product.getUserId().getUserId();
        Product product1 = productService.getProductById(productId);
        Offer offerId = findOfferById(offer.getOfferId());
        notificationService.saveNotification(PRODUCT_TAWAR, offerId, product1, userId);
        notificationService.saveNotification(DAPAT_TAWARAN, offerId, product1, sellerId);
    }

    @Override
    public List<Offer> getOfferBySeller(Integer userId) {
        return offerRepository.getOfferBySeller(userId);
    }

    @Override
    public List<Offer> getOfferByStatusDiminati(Integer userId) {
        return offerRepository.getOfferByStatusDiminati(userId);
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
    public Offer findOfferById(Long offerId) {
        return offerRepository.getById(offerId);
    }

    @Override
    public void rejectedStatus(Long offerId) {
        offerRepository.statusRejected(offerId);
    }

    @Override
    public List<Offer> getOfferDiminati() {
        return offerRepository.getOfferDiminati();
    }

}
