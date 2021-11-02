package com.example.lab2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "history";
    public static final String _ID = "id";
    public static final String A = "a";
    public static final String B = "b";
    public static final String C = "c";
    public static final String RESULT1 = "result1";
    public static final String RESULT2 = "result2";
    public static final String TYPE = "type";

    static final String DB_NAME = "history_lab6.DB";

    private static final String CREATE_TABLE = "Create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + A + " TEXT NOT NULL, "
            + B + " TEXT NOT NULL, "
            + C + " TEXT NOT NULL, "
            + RESULT1 + " TEXT NOT NULL, "
            + RESULT2 + " TEXT NOT NULL, "
            + TYPE + " TEXT NOT NULL"
            + ");";

    public DataBaseHelper(Context context) { super(context,DB_NAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
