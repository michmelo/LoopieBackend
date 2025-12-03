package com.example.loopie.Pedidos.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
}
