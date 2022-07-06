package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.offer.OfferResponse;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.service.offer.OfferService;
import com.binar.dummyproject.service.product.ProductService;
import com.binar.dummyproject.service.users.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Offer", description = "API for processing various operations with Offer entity")
@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UsersService usersService;

    @Operation(summary = "Buyer get all offer by userId")
    @GetMapping("/buyer/get-offer-history/{userId}")
    public ResponseEntity<OfferResponse> getOfferByBuyer(
            @PathVariable("userId") Integer userId
    ){
        List<Offer> offers = offerService.getAllOfferByUser(userId);
        List<OfferResponse> offerResponses =
                offers.stream().map(offer -> new OfferResponse(offer)).collect(
                        Collectors.toList());
        return new ResponseEntity(offerResponses, HttpStatus.OK);
    }

    @Operation(summary = "Seller get all offer")
    @GetMapping("/seller/get-offer-history/{userId}")
    public ResponseEntity<OfferResponse> getOfferBySeller(
            @PathVariable("userId") Integer userId,
            Long productId
    ){
        List<Offer> offers = offerService.getOfferBySeller(userId, productId);
        List<OfferResponse> offerResponses =
                offers.stream().map(offer -> new OfferResponse(offer)).collect(
                        Collectors.toList());
        return new ResponseEntity(offerResponses, HttpStatus.OK);
    }

    @Operation(summary = "Buyer add offer to product")
    @PostMapping("/buyer/add-offer/{userId}/{productId}")
    public ResponseEntity<OfferResponse> addOffer (
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Long productId,
            @RequestParam("offerId") Long offerId,
            @RequestParam("offer_price") Double offerPrice,
            @RequestParam("offer_status") String offerStatus) {

        Users users = usersService.findByUserId(userId);
        Product product = productService.getProductById(productId);
        Offer offer = new Offer();
        offer.setUserId(users);
        offer.setProductId(product);
        offer.setOfferId(offerId);
        offer.setOfferPrice(offerPrice);
        offer.setOfferStatus(offerStatus);
        LocalDateTime dateTime = LocalDateTime.now();
        offer.setLocalDateTime(dateTime);
        offerService.saveOffer(offerId, userId, productId, offerPrice, offerStatus, dateTime);

        return new ResponseEntity(new OfferResponse(offer), HttpStatus.OK);
    }
}
