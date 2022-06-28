package com.binar.dummyproject.repository.product;

import com.binar.dummyproject.model.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
