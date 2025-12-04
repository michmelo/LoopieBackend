package com.example.loopie.Pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idItemPedido;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedidoItem;

    @Column(name = "id_producto")
    private long idProducto;

    private int cantidad;

    private double precioItem;

    // Constructor vacío requerido por JPA
    public ItemPedido() {
    }

    // Getters y setters

    public Long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Pedido getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(Pedido pedidoItem) {
        this.pedidoItem = pedidoItem;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(double precioItem) {
        this.precioItem = precioItem;
    }
}
