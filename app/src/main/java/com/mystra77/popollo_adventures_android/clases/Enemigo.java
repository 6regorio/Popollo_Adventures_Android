package com.mystra77.popollo_adventures_android.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Enemigo extends Personaje implements Serializable {

    public Enemigo(String nombre, int salud, int saludMaxima, int mana, int manaMaximo, int fuerza,
                   int magia, int agilidad, int defensa, ArrayList<Habilidad> habilidadesArray,
                   int dinero, int experiencia, int imagenCombate, int imagenMuerte) {
        super(nombre, salud, saludMaxima, mana, manaMaximo, fuerza, magia, agilidad, defensa,
                habilidadesArray, dinero, experiencia, imagenCombate, imagenMuerte);
    }

}
