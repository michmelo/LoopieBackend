package com.example.loopie.Pedidos.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoRequest {
    private List<ItemPedidoRequest> items;
    private Double total;
}
