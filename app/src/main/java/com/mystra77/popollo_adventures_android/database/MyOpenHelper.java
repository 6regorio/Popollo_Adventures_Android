package com.mystra77.popollo_adventures_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mystra77.popollo_adventures_android.R;
import com.mystra77.popollo_adventures_android.clases.Habilidad;
import com.mystra77.popollo_adventures_android.clases.Heroe;
import com.mystra77.popollo_adventures_android.clases.Objeto;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {
    /**
     * Builder with the name of the database and its version, as well as String variables
     * that receive resources from Android app
     */
    public MyOpenHelper(Context context) {
        super(context, Constants.getDatabaseName(), null, Constants.getDatabaseVersion());
    }

    /**
     * The first time the application is run, it creates the database and inserts three entries
     * that generate the save points. It also adds the trigger
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.getCreateTableGame());
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getID() + ") VALUES ( 1 )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.getTableGame());
        onCreate(db);
    }

    public void saveGame(Heroe heroe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.getNOMBRE(), heroe.getNombre());
        values.put(Constants.getDINERO(), heroe.getDinero());
        values.put(Constants.getNIVEL(), heroe.getNivel());
        values.put(Constants.getEXPLORAR(), heroe.getExplorar());
        values.put(Constants.getREPUTACION(), heroe.getReputacion());
        values.put(Constants.getEXPERIENCIA(), heroe.getExperiencia());
        values.put(Constants.getSALUD(), heroe.getSalud());
        values.put(Constants.getSALUDMAXIMA(), heroe.getSaludMaxima());
        values.put(Constants.getMANA(), heroe.getMana());
        values.put(Constants.getMANAMAXIMO(), heroe.getManaMaximo());
        values.put(Constants.getFUERZA(), heroe.getFuerza());
        values.put(Constants.getMAGIA(), heroe.getMagia());
        values.put(Constants.getAGILIDAD(), heroe.getAgilidad());
        values.put(Constants.getDEFENSA(), heroe.getDefensa());
        values.put(Constants.getOBJETO1(), heroe.getObjetosArray().get(0).getCantidad());
        values.put(Constants.getOBJETO2(), heroe.getObjetosArray().get(1).getCantidad());
        values.put(Constants.getOBJETO3(), heroe.getObjetosArray().get(2).getCantidad());
        db.update(Constants.getTableGame(), values, Constants.getID() + " = (" + 1 + " );", null);
    }

    public Heroe loadGame() {
        SQLiteDatabase db = this.getReadableDatabase();
        Heroe heroe;
        Cursor result = db.query(Constants.getTableGame(), null, Constants.getID() + "=" + 1,
                null, null, null, null);
        result.moveToFirst();
        ArrayList<Habilidad> habilidadesHeroe = new ArrayList<Habilidad>();
        habilidadesHeroe.add(new Habilidad("Llamarada", 2, 4, true));
        habilidadesHeroe.add(new Habilidad("Flecha Helada", 3, 6, true));
        habilidadesHeroe.add(new Habilidad("Circulo Sanador", 5, 6, false));
        //Objetos Heroe
        ArrayList<Objeto> objetosHeroe = new ArrayList<Objeto>();
        objetosHeroe.add(new Objeto("Estrella Ninja", 25, result.getInt(result.getColumnIndex(Constants.getOBJETO1())), true, 150));
        objetosHeroe.add(new Objeto("Barril Explosivo", 60, result.getInt(result.getColumnIndex(Constants.getOBJETO2())), true, 400));
        objetosHeroe.add(new Objeto("Pocion de Mana", 30, result.getInt(result.getColumnIndex(Constants.getOBJETO3())), false, 250));
        //Atributos Heroe
        heroe = new Heroe(result.getString(result.getColumnIndex(Constants.getNOMBRE())),
                result.getInt(result.getColumnIndex(Constants.getDINERO())),
                result.getInt(result.getColumnIndex(Constants.getNIVEL())),
                result.getInt(result.getColumnIndex(Constants.getEXPLORAR())),
                result.getInt(result.getColumnIndex(Constants.getREPUTACION())),
                result.getInt(result.getColumnIndex(Constants.getEXPERIENCIA())),
                result.getInt(result.getColumnIndex(Constants.getSALUD())),
                result.getInt(result.getColumnIndex(Constants.getSALUDMAXIMA())),
                result.getInt(result.getColumnIndex(Constants.getMANA())),
                result.getInt(result.getColumnIndex(Constants.getMANAMAXIMO())),
                result.getInt(result.getColumnIndex(Constants.getFUERZA())),
                result.getInt(result.getColumnIndex(Constants.getMAGIA())),
                result.getInt(result.getColumnIndex(Constants.getAGILIDAD())),
                result.getInt(result.getColumnIndex(Constants.getDEFENSA())),
                habilidadesHeroe, objetosHeroe,
                R.drawable.combat_popollo, R.drawable.combat_popollo_death);
        return heroe;
    }

    public int registroPartidaGuardada(){
        SQLiteDatabase db = this.getReadableDatabase();
        Heroe heroe;
        Cursor result = db.query(Constants.getTableGame(), null, Constants.getID() + "=" + 1,
                null, null, null, null);
        result.moveToFirst();
        return result.getInt(result.getColumnIndex(Constants.getEXPLORAR()));
    }
}



