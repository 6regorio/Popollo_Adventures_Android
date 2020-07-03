package com.mystra77.popollo_adventures_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 1 )");
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 2 )");
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 3 )");
        db.execSQL(Constants.getUpdateTimeTrigger());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.getTableGame());
        onCreate(db);
    }
}



