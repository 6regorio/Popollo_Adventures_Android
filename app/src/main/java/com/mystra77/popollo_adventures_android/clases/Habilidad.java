package com.mystra77.popollo_adventures_android.clases;

import com.mystra77.popollo_adventures_android.exceptions.InvalidTipoHabilidadException;

public class Habilidad extends ElementoIdentificador {

    private int poderDeHabilidad;
    private int gastoDeMana;
    private tipoDeHabilidad tipoDeHabilidad;

    public Habilidad(String nombre, int poderDeHabilidad, int gastoDeMana, tipoDeHabilidad tipoDeHabilidad) throws InvalidTipoHabilidadException {
        super(nombre);
        this.poderDeHabilidad = poderDeHabilidad;
        this.gastoDeMana = gastoDeMana;
        setTipoDeHabilidad(tipoDeHabilidad);
    }

    public int getPoderDeHabilidad() {
        return poderDeHabilidad;
    }

    public void setPoderDeHabilidad(int poderDeHabilidad) {
        this.poderDeHabilidad = poderDeHabilidad;
    }

    public int getGastoDeMana() {
        return gastoDeMana;
    }

    public void setGastoDeMana(int gastoDeMana) {
        this.gastoDeMana = gastoDeMana;
    }

    public enum tipoDeHabilidad {
        OFENSIVA,
        CURATIVA
    };

    public tipoDeHabilidad getTipoDeHabilidad() {
        return tipoDeHabilidad;
    }

    public void setTipoDeHabilidad(tipoDeHabilidad tipoDeHabilidad) throws InvalidTipoHabilidadException {
        switch (tipoDeHabilidad.toString().toLowerCase()) {
            case "ofensivo":
                this.tipoDeHabilidad = tipoDeHabilidad.OFENSIVA;
                break;
            case "curativo":
                this.tipoDeHabilidad = tipoDeHabilidad.CURATIVA;
                break;
            default:
                throw new InvalidTipoHabilidadException(tipoDeHabilidad + " no es valido. Solo puede ser 'ofensivo' o 'curativo'");
        }
    }
}
