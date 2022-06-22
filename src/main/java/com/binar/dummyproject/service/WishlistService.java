package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import com.binar.dummyproject.model.Wishlist;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface WishlistService {
    void saveWishlist (Long productId, Integer userId);
    Optional<Wishlist> deleteProductById(Long wishlistId);

}
