package com.example.loopie.Pedidos.dto;

import java.util.List;

public class PedidoRequest {

    private List<ItemPedidoRequest> items;
    private Double total;

    public PedidoRequest() {
    }

    public List<ItemPedidoRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoRequest> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}