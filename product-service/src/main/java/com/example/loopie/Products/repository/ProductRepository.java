package com.example.loopie.Products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.loopie.Products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
