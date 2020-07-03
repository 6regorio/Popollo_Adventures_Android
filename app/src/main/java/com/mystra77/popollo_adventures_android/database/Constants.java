package com.mystra77.popollo_adventures_android.database;

public class Constants {

    /**
     * Constants used to help us with the database
     */
    private static final String DATABASE_NAME = "Mystra77VisualNovel";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GAME = "game";
    private static final String KEY_ID = "id";
    private static final String TIME = "time";
    private static final String STAGE = "stage";
    private static final String ANGEL = "angel";
    private static final String NEKO = "neko";
    private static final String WITCH = "witch";
    private static final String SCORE = "score";

    /**
     * String used to create the table
     */
    private static final String CREATE_TABLE_GAME = "CREATE TABLE " + TABLE_GAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, " + TIME + " TIMESTAMP DEFAULT 0, "
            + STAGE + " INTEGER DEFAULT 1," + ANGEL + " INTEGER," + NEKO + " INTEGER," + WITCH + " INTEGER," + SCORE + " INTEGER);";

    /**
     * Trigger that updates the time each time we update an entry
     */
    private static final String UPDATE_TIME_TRIGGER = "CREATE TRIGGER update_time_trigger " +
            "AFTER UPDATE ON " + TABLE_GAME + " BEGIN " +
            "UPDATE " + TABLE_GAME + " SET " + TIME + " = current_timestamp " +
            "WHERE " + KEY_ID + " = old." + KEY_ID + ";" +
            "END";

    /*
     * GETTERS that returns the value of the assigned String in each field
     */
    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getTableGame() {
        return TABLE_GAME;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getTIME() {
        return TIME;
    }

    public static String getSTAGE() {
        return STAGE;
    }

    public static String getANGEL() {
        return ANGEL;
    }

    public static String getNEKO() {
        return NEKO;
    }

    public static String getWITCH() {
        return WITCH;
    }

    public static String getSCORE() {
        return SCORE;
    }

    public static String getCreateTableGame() {
        return CREATE_TABLE_GAME;
    }

    public static String getUpdateTimeTrigger() {
        return UPDATE_TIME_TRIGGER;
    }

}
