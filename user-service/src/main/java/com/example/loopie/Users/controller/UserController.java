package com.example.loopie.Users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.loopie.Users.model.User;
import com.example.loopie.Users.dto.UserResponseDTO;
import com.example.loopie.Users.service.UserService;

import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor


public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return service.getAllUsers().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{idUsuario}")
    public UserResponseDTO getUserById(@PathVariable long idUsuario) {
        return mapToDTO(service.getUserById(idUsuario));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody User user) {
        return ResponseEntity.ok(mapToDTO(service.createUser(user)));
    }



    @PutMapping("/{idUsuario}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable long idUsuario, @RequestBody User user) {
        return ResponseEntity.ok(mapToDTO(service.updateUser(idUsuario, user)));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteUser(@PathVariable long idUsuario) {
        service.deleteUser(idUsuario);
        return ResponseEntity.noContent().build();
    }


    
    private UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .idUsuario(user.getIdUsuario())
                .username(user.getUsername())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .rol(user.getRol())
                .direccion(user.getDireccion())
                .estado(user.isEnabled())
                .build();
    }
}
