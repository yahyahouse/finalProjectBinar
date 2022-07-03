package com.binar.dummyproject.repository.product;

import com.binar.dummyproject.model.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Modifying
    @Query(value = "delete from product_image where product_id =:product_id", nativeQuery = true)
    void deleteProductImage(@Param("product_id") Long productId);

    @Modifying
    @Query(value = "select distinct on (i.product_id) i.product_id, p.user_id, i.product_image_id, i.url, p.product_id, p.product_category, p.product_description, p.product_name, p.product_price " +
            "from product p join product_image i on i.product_id = p.product_id", nativeQuery = true)
    List<ProductImage> getAllData();


}
