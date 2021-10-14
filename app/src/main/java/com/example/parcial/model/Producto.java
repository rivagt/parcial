package com.example.parcial.model;

public class Producto {
    private String uid;
    private String Nombre;
    private String Marca;
    private String Precio;
    private String Existencias;

    public Producto() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getExistencias() {
        return Existencias;
    }

    public void setExistencias(String existencias) {
        this.Existencias = existencias;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
