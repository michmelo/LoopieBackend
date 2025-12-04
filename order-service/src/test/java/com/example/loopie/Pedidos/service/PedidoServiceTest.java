package com.example.loopie.Pedidos.service;

import com.example.loopie.Pedidos.client.ProductClient;
import com.example.loopie.Pedidos.client.UserClient;
import com.example.loopie.Pedidos.dto.ProductDTO;
import com.example.loopie.Pedidos.dto.UserDTO;
import com.example.loopie.Pedidos.model.ItemPedido;
import com.example.loopie.Pedidos.model.Pedido;
import com.example.loopie.Pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;
    private ItemPedido itemPedido;
    private UserDTO userDTO;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        itemPedido = new ItemPedido();
        itemPedido.setIdItemPedido(1L);
        itemPedido.setIdProducto(1L);
        itemPedido.setCantidad(2);
        itemPedido.setPrecioItem(100.0);

        List<ItemPedido> items = new ArrayList<>();
        items.add(itemPedido);

        pedido = new Pedido();
        pedido.setIdPedido(1L);
        pedido.setIdUsuario(1L);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatusPedido("PENDIENTE");
        pedido.setItems(items);
        pedido.setTotalPedido(200.0);

        userDTO = new UserDTO();
        userDTO.setIdUsuario(1L);
        userDTO.setUsername("testuser");

        productDTO = new ProductDTO();
        productDTO.setIdProducto(1L);
        productDTO.setPrecio(100.0);
    }

    @Test
    void createPedido() {
        when(userClient.getUserById(1L)).thenReturn(userDTO);
        when(productClient.getProductById(1L)).thenReturn(productDTO);
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        List<ItemPedido> items = new ArrayList<>();
        items.add(itemPedido);

        Pedido createdPedido = pedidoService.createPedido(1L, items);

        assertNotNull(createdPedido);
        assertEquals(1L, createdPedido.getIdUsuario());
        assertEquals(200.0, createdPedido.getTotalPedido());
        verify(userClient, times(1)).getUserById(1L);
        verify(productClient, times(1)).getProductById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void createPedido_UserNotFound() {
        when(userClient.getUserById(1L)).thenReturn(null);
        List<ItemPedido> items = new ArrayList<>();
        assertThrows(RuntimeException.class, () -> pedidoService.createPedido(1L, items));
        verify(userClient, times(1)).getUserById(1L);
        verifyNoInteractions(productClient);
        verifyNoInteractions(pedidoRepository);
    }

    @Test
    void createPedido_ProductNotFound() {
        when(userClient.getUserById(1L)).thenReturn(userDTO);
        when(productClient.getProductById(1L)).thenReturn(null);

        List<ItemPedido> items = new ArrayList<>();
        items.add(itemPedido);

        assertThrows(RuntimeException.class, () -> pedidoService.createPedido(1L, items));
        verify(userClient, times(1)).getUserById(1L);
        verify(productClient, times(1)).getProductById(1L);
        verifyNoInteractions(pedidoRepository);
    }

    @Test
    void getAllPedidos() {
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void getPedidoById() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Pedido foundPedido = pedidoService.getPedidoById(1L);
        assertNotNull(foundPedido);
        assertEquals(pedido.getIdPedido(), foundPedido.getIdPedido());
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void getPedidoById_NotFound() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pedidoService.getPedidoById(1L));
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void getPedidosByUserId() {
        when(pedidoRepository.findByIdUsuario(1L)).thenReturn(Arrays.asList(pedido));
        List<Pedido> pedidos = pedidoService.getPedidosByUserId(1L);
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        verify(pedidoRepository, times(1)).findByIdUsuario(1L);
    }
}
