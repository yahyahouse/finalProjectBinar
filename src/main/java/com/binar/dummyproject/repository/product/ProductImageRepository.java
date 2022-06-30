package com.binar.dummyproject.repository.product;

import com.binar.dummyproject.model.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Modifying
    @Query(value = "delete from product_image where product_id =:product_id", nativeQuery = true)
    void deleteProductImage(@Param("product_id") Long productId);
}
