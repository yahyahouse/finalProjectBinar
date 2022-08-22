package com.binar.dummyproject.service;

import com.binar.dummyproject.model.offer.Offer;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.offer.OfferRepository;
import com.binar.dummyproject.service.offer.OfferService;
import com.binar.dummyproject.service.offer.OfferServiceImpl;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService = new OfferServiceImpl();

    @Test
    @DisplayName("User get all offers")
    void getAllOfferByUser(){
        Integer userId = 1;
        List<Offer> offers = new ArrayList<>();
        when(offerRepository.getAllByUserId(userId)).thenReturn(offers);
        assertSame(offers, offerService.getAllOfferByUser(userId));
    }

    @Test
    @DisplayName("Get offer by seller")
    void getOfferBySeller(){
        Integer sellerId = 1;
        List<Offer> offers = new ArrayList<>();
        when(offerRepository.getOfferBySeller(sellerId)).thenReturn(offers);
        assertSame(offers, offerService.getOfferBySeller(sellerId));
    }

}
