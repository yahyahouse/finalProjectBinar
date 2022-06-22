package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.model.Wishlist;
import com.binar.dummyproject.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService{
    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public void saveWishlist(Long productId, Integer userId) {
        Wishlist wishlist = new Wishlist();
        Product product = new Product();
        product.setProductId(productId);
        wishlist.setProductId(product);
        Users user = new Users();
        user.setUserId(userId);
        wishlist.setUserId(user);
        wishlistRepository.save(wishlist);
    }

    @Override
    public Optional<Wishlist> deleteProductById(Long wishlistId) {
        Optional<Wishlist> delWishlist = wishlistRepository.findById(wishlistId);
        wishlistRepository.deleteWishlistByProductIdAndUserId(wishlistId);
        return delWishlist;
    }
}
