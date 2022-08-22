package com.binar.dummyproject.model.wishlist;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import lombok.Data;

@Data
public class WishlishResponses {

    Long wishlistId;
    Long productId;
    Integer userId;
    String productName;
    Integer productPrice;
    String fullName;


    public WishlishResponses(){

    }

    public WishlishResponses(Users users, Product product, Wishlist wishlist) {
        this.wishlistId = wishlist.getWishlistId();
        this.userId = users.getUserId();
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.fullName = users.getFullNameUser();
    }
}
