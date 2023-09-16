package com.upc.examen_matos.entidades;

public class Plato {
    private int id;
    private String nombre, categoria, observaciones;
    private double precio;
    private int cantidad;
    private String fechaPedido;

    public Plato(int id, String nombre, String categoria, String observaciones, double precio, int cantidad, String fechaPedido) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.observaciones = observaciones;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
    }

    public Plato(String nombre, String categoria, String observaciones, double precio, int cantidad, String fechaPedido) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.observaciones = observaciones;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
