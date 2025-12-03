package com.example.loopie.Pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
