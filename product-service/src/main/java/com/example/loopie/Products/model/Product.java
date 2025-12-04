package com.example.loopie.Products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long idProducto;

    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "descripcion")
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @Column(name = "precio")
    @NotNull(message = "El precio no puede ser nulo")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero")
    private Double precio;

    @Column(name = "categoria")
    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoria;

    @Column(name = "stock")
    @NotNull(message = "El stock no puede ser nulo")
    @Min(0)
    private Integer stock;

    @Column(name = "imagen")
    @NotBlank(message = "La imagen no puede estar vacía")
    private String imagen;

    @Column(name = "enOferta")
    @NotNull(message = "El campo enOferta no puede ser nulo")
    private Boolean enOferta;

    @Column(name = "precioOferta")
    @PositiveOrZero(message = "El precio de oferta debe ser mayor o igual a cero")
    private Double precioOferta;

    @Column(name = "tienda")
    @NotBlank(message = "La tienda no puede estar vacía")
    private String tienda;

    @Column(name = "talla")
    @NotBlank(message = "La talla no puede estar vacía")
    private String talla;

    @Column(name = "tag")
    @NotBlank(message = "El tag no puede estar vacío")
    private String tag;

    @Column(name = "isVintage")
    @NotNull(message = "El campo isVintage no puede ser nulo")
    private Boolean isVintage;

    public Product() {
    }

    // Getters y Setters

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getEnOferta() {
        return enOferta;
    }

    public void setEnOferta(Boolean enOferta) {
        this.enOferta = enOferta;
    }

    public Double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(Double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getIsVintage() {
        return isVintage;
    }

    public void setIsVintage(Boolean isVintage) {
        this.isVintage = isVintage;
    }
}