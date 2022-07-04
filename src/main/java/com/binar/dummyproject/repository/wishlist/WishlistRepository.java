package com.binar.dummyproject.repository.wishlist;

import com.binar.dummyproject.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Modifying
    @Query(value = "delete from wishlist where wishlist_id =:wishlist_id", nativeQuery = true)
    void deleteWishlistByProductIdAndUserId(@Param("wishlist_id") Long wishlist_id);

    @Modifying
    @Query(value = "select w.wishlist_id, u.user_id, p.product_id, p.product_name, p.product_price " +
            "from wishlist w join product p on p.product_id = w.product_id " +
            "join users u on u.user_id = w.user_id where w.user_id=:user_id", nativeQuery = true)
    List<Wishlist> getWishlistsByUserId(@Param("user_id") Integer user_id);
}
