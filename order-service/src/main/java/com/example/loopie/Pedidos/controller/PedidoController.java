package com.example.loopie.Pedidos.controller;

import com.example.loopie.Pedidos.dto.PedidoRequest;
import com.example.loopie.Pedidos.model.ItemPedido;
import com.example.loopie.Pedidos.model.Pedido;
import com.example.loopie.Pedidos.service.PedidoService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoRequest request) {
        // TODO: Extraer userId del token JWT.
        int userId = 1; 
        
        List<ItemPedido> items = request.getItems().stream().map(itemRequest -> {
            ItemPedido item = new ItemPedido();
            item.setIdProducto(itemRequest.getIdProducto());
            item.setCantidad(itemRequest.getCantidad());
            return item;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(pedidoService.createPedido(userId, items));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pedido>> getPedidosByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(pedidoService.getPedidosByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }
}
