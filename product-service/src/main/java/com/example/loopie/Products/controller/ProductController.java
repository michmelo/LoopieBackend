package com.example.loopie.Products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.loopie.Products.model.Product;
import com.example.loopie.Products.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor



public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Product> getProductById(@PathVariable long idProducto) {
        return ResponseEntity.ok(productService.getProductById(idProducto));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Product> updateProduct(@PathVariable long idProducto, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(idProducto, product));
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long idProducto) {
        productService.deleteProduct(idProducto);
        return ResponseEntity.noContent().build();
    }

}