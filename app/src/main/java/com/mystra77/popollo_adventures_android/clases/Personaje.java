package com.mystra77.popollo_adventures_android.clases;

import com.mystra77.popollo_adventures_android.exceptions.InvalidadNumeroHabilidadException;

import java.util.ArrayList;
import java.util.Random;

public class Personaje {

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

    public Personaje(String nombre, int salud, int saludMaxima, int mana, int manaMaximo, int fuerza, int magia, int agilidad, int defensa, ArrayList<Habilidad> habilidadesArray, int dinero, int experiencia) throws InvalidadNumeroHabilidadException {
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
    }

    public Personaje(String nombre, int salud, int saludMaxima, int fuerza, int agilidad, int defensa) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.defensa = defensa;
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

    public void setHabilidadesArray(ArrayList<Habilidad> habilidadesArray) throws InvalidadNumeroHabilidadException {
        if (habilidadesArray.size() == 3) {
            this.habilidadesArray = habilidadesArray;
        } else {
            throw new InvalidadNumeroHabilidadException("El numero de habilidades deben ser 3 obligatoriamente.");
        }

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
            return resultado = "**GOLPE CRITICO** " + objetivo.nombre + " recibe " + dañoRealizado + " puntos de daño.";
        } else if (fallo) {
            return resultado = nombre + " **Falla el ataque**";
        } else {
            if (dañoRealizado > 0) {
                objetivo.setSalud(objetivo.salud - dañoRealizado);
                return resultado = objetivo.nombre + " recibe " + dañoRealizado + " puntos de daño.";
            } else {
                return resultado = objetivo.nombre + " **Bloquea el ataque**";
            }
        }
    }

}
