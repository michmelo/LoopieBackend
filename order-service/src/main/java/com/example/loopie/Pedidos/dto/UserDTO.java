package com.example.loopie.Pedidos.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long idUsuario;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private String direccion;
}
