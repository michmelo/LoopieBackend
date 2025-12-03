package com.example.loopie.Pedidos.controller;

import com.example.loopie.Pedidos.dto.ItemPedidoRequest;
import com.example.loopie.Pedidos.dto.PedidoRequest;
import com.example.loopie.Pedidos.model.ItemPedido;
import com.example.loopie.Pedidos.model.Pedido;
import com.example.loopie.Pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private Pedido pedido;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
        objectMapper = new ObjectMapper();

        pedido = new Pedido();
        pedido.setIdPedido(1L);
        pedido.setIdUsuario(1L);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatusPedido("PENDIENTE");
        pedido.setTotalPedido(200.0);
    }

    @Test
    void createPedido() throws Exception {
        PedidoRequest request = new PedidoRequest();
        List<ItemPedidoRequest> items = new ArrayList<>();
        ItemPedidoRequest itemRequest = new ItemPedidoRequest();
        itemRequest.setIdProducto(1L);
        itemRequest.setCantidad(2);
        items.add(itemRequest);
        request.setItems(items);

        when(pedidoService.createPedido(anyLong(), anyList())).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusPedido").value("PENDIENTE"));
    }

    @Test
    void getPedidosByUserId() throws Exception {
        when(pedidoService.getPedidosByUserId(1L)).thenReturn(Arrays.asList(pedido));

        mockMvc.perform(get("/api/v1/pedidos/user/{idUsuario}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].statusPedido").value("PENDIENTE"));
    }

    @Test
    void getPedidoById() throws Exception {
        when(pedidoService.getPedidoById(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedidos/{idPedido}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusPedido").value("PENDIENTE"));
    }
}
