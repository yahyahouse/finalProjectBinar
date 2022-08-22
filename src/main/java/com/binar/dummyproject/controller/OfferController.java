package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.offer.OfferResponse;
import com.binar.dummyproject.model.offer.OfferResponseNew;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductResponse;
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
import java.util.stream.Collectors;

import static com.binar.dummyproject.model.InfoConst.*;

@Tag(name = "Offer", description = "API for processing various operations with Offer entity")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        List<OfferResponseNew> offerResponsesNew =
                offers.stream().map(OfferResponseNew::new).collect(
                        Collectors.toList());
        return new ResponseEntity(offerResponsesNew, HttpStatus.OK);
    }

    @Operation(summary = "Seller get all offer by id")
    @GetMapping("/seller/get-offer-history/{userId}")
    public ResponseEntity<OfferResponseNew> getOfferBySeller(
            @PathVariable("userId") Integer userId
    ){
        List<Offer> offers = offerService.getOfferBySeller(userId);
        List<OfferResponseNew> offerResponsesNew =
                offers.stream().map(OfferResponseNew::new).collect(
                        Collectors.toList());
        return new ResponseEntity(offerResponsesNew, HttpStatus.OK);
    }

    @Operation(summary = "Buyer add offer to product")
    @PostMapping("/buyer/add-offer/{userId}/{productId}")
    public ResponseEntity<OfferResponse> addOffer (
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Long productId,
            @RequestParam("offer_price") Double offerPrice,
            @RequestParam(defaultValue = "Diminati", required = false) String offerStatus) {

        Users users = usersService.findByUserId(userId);
        Product product = productService.getProductById(productId);
        Offer offer = new Offer();
        offer.setUserId(users);
        offer.setProductId(product);
        offer.setOfferPrice(offerPrice);
        offer.setOfferStatus(offerStatus);
        LocalDateTime dateTime = LocalDateTime.now();
        offer.setLocalDateTime(dateTime);
        offerService.saveOffer(userId, productId, offerPrice, offerStatus, dateTime);

        return new ResponseEntity(new OfferResponse(offer), HttpStatus.OK);
    }

    @Operation(summary = "Get product diminati by UserId")
    @GetMapping(value = "/seller/get-diminati/{userId}")
    public ResponseEntity<ProductResponse> getDetailOfferDiminatiById(
            @PathVariable("userId") Integer userId){
        List<Offer> offer = offerService.getOfferByStatusDiminati(userId);
        List<OfferResponseNew> offerResponseNew =
                offer.stream().map(OfferResponseNew::new)
                        .collect(Collectors.toList());
        return new ResponseEntity(offerResponseNew, HttpStatus.OK);
    }

    @Operation(summary = "Get product and offer with 'sold' status by Id")
    @GetMapping(value = "/seller/get-product-sold/{userId}")
    public ResponseEntity<ProductResponse> getOfferandProductSold(
            @PathVariable("userId") Integer userId){
        List<Offer> offer = offerService.getOfferByOfferStatusAndProductSold(userId);
        List<OfferResponseNew> offerResponseNew =
                offer.stream().map(OfferResponseNew::new)
                        .collect(Collectors.toList());
        return new ResponseEntity(offerResponseNew, HttpStatus.OK);
    }

    @Operation(summary = "Update transaction status to accept the offer")
    @PostMapping("/seller/accepted-status/{offerId}")
    public ResponseEntity<OfferResponse> accStatus(
            @PathVariable("offerId") Long offerId){
        offerService.acceptedStatus(offerId);
        return new ResponseEntity(TERIMA_PENAWARAN,HttpStatus.OK);
    }

    @Operation(summary = "Update transaction status to reject the offer")
    @PostMapping("/seller/rejected-status/{offerId}")
    public ResponseEntity<OfferResponse> rejectStatus(
            @PathVariable("offerId") Long offerId){
        offerService.rejectedStatus(offerId);
        return new ResponseEntity(TOLAK_PENAWARAN,HttpStatus.OK);
    }

    @Operation(summary = "Get detail product By offer Id")
    @GetMapping("/get-detail-offer/{offerId}")
    public ResponseEntity<OfferResponseNew> getDetailOfferById(@PathVariable ("offerId") Long offerId){
        Offer offer = offerService.findOfferById(offerId);
        return new ResponseEntity<>(new OfferResponseNew(offer),HttpStatus.OK);
    }

}
