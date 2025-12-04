package com.example.loopie.Pedidos.controller;

import com.example.loopie.Pedidos.dto.PedidoRequest;
import com.example.loopie.Pedidos.model.ItemPedido;
import com.example.loopie.Pedidos.model.Pedido;
import com.example.loopie.Pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoRequest request) {
        // TODO: Extraer userId del token JWT.
        Long userId = 1L;

        List<ItemPedido> items = request.getItems().stream().map(itemRequest -> {
            ItemPedido item = new ItemPedido();
            item.setIdProducto(itemRequest.getIdProducto());
            item.setCantidad(itemRequest.getCantidad());
            return item;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(pedidoService.createPedido(userId, items));
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<List<Pedido>> getPedidosByUserId(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(pedidoService.getPedidosByUserId(idUsuario));
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long idPedido) {
        return ResponseEntity.ok(pedidoService.getPedidoById(idPedido));
    }
}
