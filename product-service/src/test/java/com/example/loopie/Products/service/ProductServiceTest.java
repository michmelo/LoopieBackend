package com.example.loopie.Products.service;

import com.example.loopie.Products.model.Product;
import com.example.loopie.Products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setIdProducto(1L);
        product.setNombre("Test Product");
        product.setDescripcion("Test Description");
        product.setPrecio(100.0);
        product.setCategoria("Test Category");
        product.setStock(10);
        product.setImagen("test.jpg");
        product.setEnOferta(false);
        product.setPrecioOferta(0.0);
        product.setTienda("Test Store");
        product.setTalla("M");
        product.setTag("test");
        product.setIsVintage(false);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(1, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product foundProduct = productService.getProductById(1L);
        assertNotNull(foundProduct);
        assertEquals(product.getIdProducto(), foundProduct.getIdProducto());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void createProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertEquals(product.getIdProducto(), createdProduct.getIdProducto());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedDetails = new Product();
        updatedDetails.setNombre("Updated Name");
        updatedDetails.setDescripcion("Updated Description");
        updatedDetails.setPrecio(150.0);
        updatedDetails.setCategoria("Updated Category");
        updatedDetails.setStock(20);
        updatedDetails.setImagen("updated.jpg");
        updatedDetails.setEnOferta(true);
        updatedDetails.setPrecioOferta(120.0);
        updatedDetails.setTienda("Updated Store");
        updatedDetails.setTalla("L");
        updatedDetails.setTag("updated");
        updatedDetails.setIsVintage(true);

        Product result = productService.updateProduct(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Name", result.getNombre());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
