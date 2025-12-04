package com.example.loopie.Pedidos.service;

import com.example.loopie.Pedidos.client.ProductClient;
import com.example.loopie.Pedidos.client.UserClient;
import com.example.loopie.Pedidos.dto.ProductDTO;
import com.example.loopie.Pedidos.dto.UserDTO;
import com.example.loopie.Pedidos.model.ItemPedido;
import com.example.loopie.Pedidos.model.Pedido;
import com.example.loopie.Pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    @Transactional
    public Pedido createPedido(Long userId, List<ItemPedido> items) {
        // Validar usuario vía Feign
        UserDTO user = userClient.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setIdUsuario(userId);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatusPedido("PENDIENTE");

        double total = 0;
        for (ItemPedido item : items) {
            // Validar producto vía Feign
            ProductDTO product = productClient.getProductById(item.getIdProducto());
            if (product == null) {
                throw new RuntimeException("Producto no encontrado: " + item.getIdProducto());
            }
            item.setPrecioItem(product.getPrecio());
            item.setPedidoItem(pedido);
            total += item.getCantidad() * item.getPrecioItem();
        }

        pedido.setItems(items);
        pedido.setTotalPedido(total);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Pedido> getPedidosByUserId(Long userId) {
        return pedidoRepository.findByIdUsuario(userId);
    }
}