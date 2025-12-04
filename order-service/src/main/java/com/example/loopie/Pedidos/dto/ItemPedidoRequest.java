package com.example.loopie.Pedidos.dto;

public class ItemPedidoRequest {

    private Long idProducto;
    private int cantidad;

    public ItemPedidoRequest() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
