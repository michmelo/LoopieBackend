package com.example.loopie.Users.dto;

import com.example.loopie.Users.model.User;

public class UserResponseDTO {

    private Long idUsuario;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private String rol;
    private String direccion;
    private boolean estado; // <- NUEVO

    public UserResponseDTO() {
    }

    // Getters y setters

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // ===== Builder manual =====

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long idUsuario;
        private String username;
        private String email;
        private String nombre;
        private String apellido;
        private String rol;
        private String direccion;
        private boolean estado; // <- NUEVO

        public Builder idUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder rol(String rol) {
            this.rol = rol;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder estado(boolean estado) { // <- NUEVO
            this.estado = estado;
            return this;
        }

        public UserResponseDTO build() {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setIdUsuario(this.idUsuario);
            dto.setUsername(this.username);
            dto.setEmail(this.email);
            dto.setNombre(this.nombre);
            dto.setApellido(this.apellido);
            dto.setRol(this.rol);
            dto.setDireccion(this.direccion);
            dto.setEstado(this.estado);
            return dto;
        }
    }

    // Opcional: helper para construir desde la entidad User
    public static UserResponseDTO fromUser(User user) {
        return UserResponseDTO.builder()
                .idUsuario(user.getIdUsuario())
                .username(user.getUsername())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .rol(user.getRol())
                .direccion(user.getDireccion())
                .estado(true)
                .build();
    }
}
