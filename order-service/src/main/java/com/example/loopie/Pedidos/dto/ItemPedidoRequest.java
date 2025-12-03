package com.example.loopie.Pedidos.dto;

import lombok.Data;

@Data
public class ItemPedidoRequest {
    private Long idProducto;
    private int cantidad;
}
