package com.example.loopie.Products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.loopie.Products.model.Product;
import com.example.loopie.Products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(long idProducto) {
        return productRepository.findById(idProducto)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(long idProducto, Product productDetails) {
        Product product = productRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        product.setNombre(productDetails.getNombre());
        product.setDescripcion(productDetails.getDescripcion());
        product.setPrecio(productDetails.getPrecio());
        product.setCategoria(productDetails.getCategoria());
        product.setStock(productDetails.getStock());
        product.setImagen(productDetails.getImagen());
        product.setEnOferta(productDetails.getEnOferta());
        product.setPrecioOferta(productDetails.getPrecioOferta());
        product.setTienda(productDetails.getTienda());
        product.setTalla(productDetails.getTalla());
        product.setTag(productDetails.getTag());
        return productRepository.save(product);
    }
    
    public void deleteProduct(long idProducto) {
        productRepository.deleteById(idProducto);
    }
    
}
