package com.mystra77.popollo_adventures_android.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Heroe extends Personaje implements Serializable {
    private int nivel;
    private ArrayList<Objeto> objetosArray;
    private int reputacion;
    private int explorar;

    public Heroe(String nombre, int dinero, int nivel, int explorar, int reputacion, int experiencia,
                 int salud, int saludMaxima, int mana, int manaMaximo, int fuerza, int magia,
                 int agilidad, int defensa, ArrayList<Habilidad> habilidadesArray,
                 ArrayList<Objeto> objetosArray, int imagenCombate, int imagenMuerte) {
        super(nombre, salud, saludMaxima, mana, manaMaximo, fuerza, magia, agilidad, defensa,
                habilidadesArray, dinero, experiencia, imagenCombate, imagenMuerte);
        this.nivel = nivel;
        this.objetosArray = objetosArray;
        this.reputacion = reputacion;
        this.explorar = explorar;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Objeto> getObjetosArray() {
        return objetosArray;
    }

    public void setObjetosArray(ArrayList<Objeto> objetosArray) {
        this.objetosArray = objetosArray;
    }

    public int getReputacion() {
        return reputacion;
    }

    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }

    public int getExplorar() {
        return explorar;
    }

    public void setExplorar(int explorar) {
        this.explorar = explorar;
    }
}
