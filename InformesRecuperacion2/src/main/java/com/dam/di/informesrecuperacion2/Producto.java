package com.dam.di.informesrecuperacion2;

import java.time.LocalDate;

public class Producto {
    Integer codigo;
    String nombre;
    Double precio;
    LocalDate fechaCaducidad;

    public Producto(int codigo, String nombre, double precio, LocalDate fechaCaducidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}
