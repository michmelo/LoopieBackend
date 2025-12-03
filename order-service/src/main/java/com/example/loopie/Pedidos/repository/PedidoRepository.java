package com.example.loopie.Pedidos.repository;

import com.example.loopie.Pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUserId(int userId);
}
