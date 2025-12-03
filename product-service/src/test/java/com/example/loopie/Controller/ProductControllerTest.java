package com.example.loopie.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;

import com.example.loopie.Products.controller.ProductController;
import com.example.loopie.Products.model.Product;
import com.example.loopie.Products.service.ProductService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService servicio;

    @Test
    public void testGetAllProducts()throws Exception {
        Product product1 = new Product(1, "Chaqueta Vintage", "Chaqueta de cuero estilo vintage, forro interior acolchado", 129.99, "Abrigo", 12, "https://cdn.example.com/products/chaqueta_vintage.jpg", true, 99.99, "VintageStore", "L", "vintage,cuero,otoño", true);
        Product product2 = new Product(2, "Zapatos Deportivos", "Zapatos deportivos ligeros y transpirables para correr", 89.99, "Calzado", 30, "https://cdn.example.com/products/zapatos_deportivos.jpg", true, 69.99, "Sporty", "42", "deporte,correr,ligero", true);

        when(servicio.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Chaqueta Vintage"))
                .andExpect(jsonPath("$[1].nombre").value("Zapatos Deportivos"));
    }

    @Test
    public void testGetProductById()throws Exception {
        Product product = new Product(1, "Chaqueta Vintage", "Chaqueta de cuero estilo vintage, forro interior acolchado", 129.99, "Abrigo", 12, "https://cdn.example.com/products/chaqueta_vintage.jpg", true, 99.99, "VintageStore", "L", "vintage,cuero,otoño", true);

        when(servicio.getProductById(1)).thenReturn(product);

        mockMvc.perform(get("/api/v1/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Chaqueta Vintage"));
    }

    @Test
    public void testCreateProduct()throws Exception {
        Product product = new Product(1, "Chaqueta Vintage", "Chaqueta de cuero estilo vintage, forro interior acolchado", 129.99, "Abrigo", 12, "https://cdn.example.com/products/chaqueta_vintage.jpg", true, 99.99, "VintageStore", "L", "vintage,cuero,otoño", true);

        when(servicio.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/products")
                .contentType("application/json")
                .content("{\"id\":1,\"nombre\":\"Chaqueta Vintage\",\"descripcion\":\"Chaqueta de cuero estilo vintage, forro interior acolchado\",\"precio\":129.99,\"categoria\":\"Abrigo\",\"stock\":12,\"imagen\":\"https://cdn.example.com/products/chaqueta_vintage.jpg\",\"enOferta\":true,\"precioOferta\":99.99,\"tienda\":\"VintageStore\",\"talla\":\"L\",\"tag\":\"vintage,cuero,otoño\",\"isVintage\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Chaqueta Vintage"));
    }

    @Test
    public void testSaveProduct()throws Exception {
        Product product = new Product(1, "Chaqueta Vintage", "Chaqueta de cuero estilo vintage, forro interior acolchado", 129.99, "Abrigo", 12, "https://cdn.example.com/products/chaqueta_vintage.jpg", true, 99.99, "VintageStore", "L", "vintage,cuero,otoño", true);

        when(servicio.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/products/add")
                .contentType("application/json")
                .content("{\"id\":1,\"nombre\":\"Chaqueta Vintage\",\"descripcion\":\"Chaqueta de cuero estilo vintage, forro interior acolchado\",\"precio\":129.99,\"categoria\":\"Abrigo\",\"stock\":12,\"imagen\":\"https://cdn.example.com/products/chaqueta_vintage.jpg\",\"enOferta\":true,\"precioOferta\":99.99,\"tienda\":\"VintageStore\",\"talla\":\"L\",\"tag\":\"vintage,cuero,otoño\",\"isVintage\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Chaqueta Vintage"));
    }

    @Test
    public void testUpdateProduct()throws Exception {
        Product product = new Product(1, "Chaqueta Vintage", "Chaqueta de cuero estilo vintage, forro interior acolchado", 129.99, "Abrigo", 12, "https://cdn.example.com/products/chaqueta_vintage.jpg", true, 99.99, "VintageStore", "L", "vintage,cuero,otoño", true);

        when(servicio.updateProduct(1, product)).thenReturn(product);

        mockMvc.perform(put("/api/v1/products/{id}", 1)
                .contentType("application/json")
                .content("{\"id\":1,\"nombre\":\"Chaqueta Vintage\",\"descripcion\":\"Chaqueta de cuero estilo vintage, forro interior acolchado\",\"precio\":129.99,\"categoria\":\"Abrigo\",\"stock\":12,\"imagen\":\"https://cdn.example.com/products/chaqueta_vintage.jpg\",\"enOferta\":true,\"precioOferta\":99.99,\"tienda\":\"VintageStore\",\"talla\":\"L\",\"tag\":\"vintage,cuero,otoño\",\"isVintage\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Chaqueta Vintage"));
    }

    @Test
    public void testDeleteProduct()throws Exception {
        mockMvc.perform(delete("/api/v1/products/{id}", 1))
                .andExpect(status().isNoContent());
    }

}

