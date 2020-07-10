package com.mystra77.popollo_adventures_android.database;

public class Constants {

    /**
     * Constants used to help us with the database
     */
    private static final String DATABASE_NAME = "Popollo_Adventures";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GAME = "Popollo";
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String DINERO = "dinero";
    private static final String NIVEL = "nivel";
    private static final String EXPLORAR = "explorar";
    private static final String REPUTACION = "reputacion";
    private static final String EXPERIENCIA = "experiencia";
    private static final String SALUD = "salud";
    private static final String SALUDMAXIMA = "saludmaxima";
    private static final String MANA = "mana";
    private static final String MANAMAXIMO = "manamaximo";
    private static final String FUERZA = "fuerza";
    private static final String MAGIA = "magia";
    private static final String AGILIDAD = "agilidad";
    private static final String DEFENSA = "defensa";
    private static final String OBJETO1 = "objeto1";
    private static final String OBJETO2 = "objeto2";
    private static final String OBJETO3 = "objeto3";

    /**
     * String used to create the table
     */
    private static final String CREATE_TABLE_GAME = "CREATE TABLE " + TABLE_GAME + "("
            + ID + " INTEGER PRIMARY KEY NOT NULL, "
            + NOMBRE + " VARCHAR(20), "
            + DINERO + " INTEGER,"
            + NIVEL + " INTEGER,"
            + EXPLORAR + " INTEGER DEFAULT 0,"
            + REPUTACION + " INTEGER,"
            + EXPERIENCIA + " INTEGER,"
            + SALUD + " INTEGER,"
            + SALUDMAXIMA + " INTEGER,"
            + MANA + " INTEGER,"
            + MANAMAXIMO + " INTEGER,"
            + FUERZA + " INTEGER,"
            + MAGIA + " INTEGER,"
            + AGILIDAD + " INTEGER,"
            + DEFENSA + " INTEGER,"
            + OBJETO1 + " INTEGER,"
            + OBJETO2 + " INTEGER,"
            + OBJETO3 + " INTEGER);";

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getTableGame() {
        return TABLE_GAME;
    }

    public static String getID() {
        return ID;
    }

    public static String getNOMBRE() {
        return NOMBRE;
    }

    public static String getDINERO() {
        return DINERO;
    }

    public static String getNIVEL() {
        return NIVEL;
    }

    public static String getEXPLORAR() {
        return EXPLORAR;
    }

    public static String getREPUTACION() {
        return REPUTACION;
    }

    public static String getEXPERIENCIA() {
        return EXPERIENCIA;
    }

    public static String getSALUD() {
        return SALUD;
    }

    public static String getSALUDMAXIMA() {
        return SALUDMAXIMA;
    }

    public static String getMANA() {
        return MANA;
    }

    public static String getMANAMAXIMO() { return MANAMAXIMO; }

    public static String getFUERZA() {
        return FUERZA;
    }

    public static String getMAGIA() {
        return MAGIA;
    }

    public static String getAGILIDAD() {
        return AGILIDAD;
    }

    public static String getDEFENSA() {
        return DEFENSA;
    }

    public static String getOBJETO1() {
        return OBJETO1;
    }

    public static String getOBJETO2() {
        return OBJETO2;
    }

    public static String getOBJETO3() {
        return OBJETO3;
    }

    public static String getCreateTableGame() { return CREATE_TABLE_GAME; }
}
