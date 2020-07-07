package com.mystra77.popollo_adventures_android.clases;

import java.io.Serializable;

public class Objeto implements Serializable {
    private String nombre;
    private int poder;
    private int cantidad;
    private Boolean ofensivo;
    private int precio;

    public Objeto(String nombre, int poder, int cantidad, Boolean ofensivo, int precio) {
        this.nombre = nombre;
        this.poder = poder;
        this.cantidad = cantidad;
        this.ofensivo = ofensivo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getOfensivo() {
        return ofensivo;
    }

    /**
     * True = objeto ofensivo - False = objeto curativo
     * @param ofensivo
     */
    public void setOfensivo(Boolean ofensivo) {
        this.ofensivo = ofensivo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
