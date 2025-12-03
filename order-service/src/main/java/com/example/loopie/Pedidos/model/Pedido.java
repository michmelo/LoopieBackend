package com.example.loopie.Pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "user_id")
    private int userId;

    @OneToMany(mappedBy = "pedidoItem", cascade = CascadeType.ALL)
    private List<ItemPedido> items;

    private Double totalPedido;

    private String statusPedido;

    private LocalDateTime fechaPedido;
}
