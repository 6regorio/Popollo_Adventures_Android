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

    public String lanzarObjeto(Personaje objetivo, int numeroObjeto){
        String resultado;
        int efecto = objetosArray.get(numeroObjeto).getPoder();
        objetosArray.get(numeroObjeto).setCantidad(objetosArray.get(numeroObjeto).getCantidad() - 1);
        if(objetosArray.get(numeroObjeto).getOfensivo()){
            objetivo.setSalud(objetivo.getSalud() - efecto);
            return resultado = objetivo.getNombre() + " recibe " + efecto + " puntos de daño.";
        }else{
            setMana(getMana() + efecto);
            if (getMana() > getManaMaximo()){
                setMana(getManaMaximo());
            }
            return resultado = getNombre() + " restablece " + efecto + " puntos de mana.";
        }
    }

    /**
     * Funcion que nos permite subir el nivel del heroe si llega a 30 puntos de experiencia * nivel y muestra todo el
     *
     * @param experienciaRecibida Variable de tipo entero que aumenta la experiencia del heroe
     */
    public String subirNivel(int experienciaRecibida) {
        String resultado;
        setExperiencia(getExperiencia() + experienciaRecibida);
        if (getExperiencia() >= getNivel() * 30) {
            setExperiencia(getExperiencia() - getNivel() * 30);
            if (nivel % 2 == 0) {
                setManaMaximo(getManaMaximo() + 2);
                setMana(getMana() + 2);
                setMagia(getMagia() + 1);
                setAgilidad(getAgilidad() + 1);
                setNivel(getNivel() + 1);
                return resultado = "¡¡¡LEVEL UP!!!";
            } else {
                setSaludMaxima(getSaludMaxima() + 10);
                setSalud(getSalud() + 10);
                setFuerza(getFuerza() + 2);
                setDefensa(getDefensa() + 1);
                setNivel(getNivel() + 1);
                return resultado = "¡¡¡LEVEL UP!!!";
            }
        }else{
            return resultado="";
        }
    }
}
