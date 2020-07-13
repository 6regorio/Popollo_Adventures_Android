package com.mystra77.popollo_adventures_android.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Personaje implements Serializable {

    private String nombre;
    private int salud;
    private int saludMaxima;
    private int mana;
    private int manaMaximo;
    private int fuerza;
    private int magia;
    private int agilidad;
    private int defensa;
    private ArrayList<Habilidad> habilidadesArray;
    private int dinero;
    private int experiencia;
    private int imagenCombate;
    private int imagenMuerte;

    public Personaje(String nombre, int salud, int saludMaxima, int mana, int manaMaximo, int fuerza,
                     int magia, int agilidad, int defensa, ArrayList<Habilidad> habilidadesArray,
                     int dinero, int experiencia, int imagenCombate, int imagenMuerte) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.mana = mana;
        this.manaMaximo = manaMaximo;
        this.fuerza = fuerza;
        this.magia = magia;
        this.agilidad = agilidad;
        this.defensa = defensa;
        this.habilidadesArray = habilidadesArray;
        this.dinero = dinero;
        this.experiencia = experiencia;
        this.imagenCombate = imagenCombate;
        this.imagenMuerte = imagenMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getSaludMaxima() {
        return saludMaxima;
    }

    public void setSaludMaxima(int saludMaxima) {
        this.saludMaxima = saludMaxima;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaMaximo() {
        return manaMaximo;
    }

    public void setManaMaximo(int manaMaximo) {
        this.manaMaximo = manaMaximo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public ArrayList<Habilidad> getHabilidadesArray() {
        return habilidadesArray;
    }

    public void setHabilidadesArray(ArrayList<Habilidad> habilidadesArray) {
        this.habilidadesArray = habilidadesArray;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getImagenCombate() {
        return imagenCombate;
    }

    public void setImagenCombate(int imagenCombate) {
        this.imagenCombate = imagenCombate;
    }

    public int getImagenMuerte() {
        return imagenMuerte;
    }

    public void setImagenMuerte(int imagenMuerte) {
        this.imagenMuerte = imagenMuerte;
    }

    //Funciones
    public String atacarObjetivo(Personaje objetivo) {
        boolean critico = false;
        boolean fallo = false;
        int dañoRealizado = 0;
        int numeroAleatorio = new Random().nextInt(3);
        String resultado = "";

        //Si la agilidad es mayor puede ocurrir un golpe critico que hace el doble de daño, si es menor puedes fallar el golpe.
        if (agilidad > objetivo.agilidad) {
            if (numeroAleatorio == 0) {
                critico = true;
                dañoRealizado = fuerza * 2;
            } else {
                dañoRealizado = fuerza - objetivo.defensa;
            }
        } else if (agilidad == objetivo.agilidad) {
            dañoRealizado = fuerza - objetivo.defensa;
        } else {
            if (numeroAleatorio == 0) {
                fallo = true;
            } else {
                dañoRealizado = fuerza - objetivo.defensa;
            }
        }
        //Comprobacion de si el golpe es critico o ha fallado.
        if (critico) {
            objetivo.setSalud(objetivo.salud - dañoRealizado);
            return resultado = objetivo.nombre + " recibe " + dañoRealizado + " puntos de daño.";
        } else if (fallo) {
            return resultado = nombre + " no logra acertar el ataque.";
        } else {
            if (dañoRealizado > 0) {
                objetivo.setSalud(objetivo.salud - dañoRealizado);
                return resultado = objetivo.nombre + " recibe " + dañoRealizado + " puntos de daño.";
            } else {
                return resultado = objetivo.nombre + " bloquea con exito el ataque.";
            }
        }
    }

    public String lanzarHechizo(Personaje objetivo, int numeroHabilidad){
        String resultado;
        int efecto = habilidadesArray.get(numeroHabilidad).getPoder() * magia;
        setMana(getMana() - habilidadesArray.get(numeroHabilidad).getCoste());
        if (habilidadesArray.get(numeroHabilidad).getOfensivo()){
            objetivo.setSalud(objetivo.salud - efecto);
            return resultado = objetivo.nombre + " recibe " + efecto + " puntos de daño.";
        }else{
            setSalud(getSalud() + efecto);
            if (getSalud() > getSaludMaxima()){
                setSalud(getSaludMaxima());
            }
            return resultado = nombre + " restablece " + efecto + " puntos de salud.";
        }
    }

}
