package com.mystra77.popollo_adventures_android.clases;

public abstract class ElementoIdentificador {

    private String nombre;

    public ElementoIdentificador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
