package com.example.lab2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager {
    private DataBaseHelper dbHelper;

    private Context context;
    private SQLiteDatabase database;

    public DataBaseManager(Context c) { context = c;}

    public DataBaseManager open() throws SQLException{
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() { dbHelper.close();}

    /*private void insert(String a1, String b1, String c1, String res1, String res2, String type1){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.A,a1);
        contentValue.put(DataBaseHelper.B,b1);
        contentValue.put(DataBaseHelper.C,c1);
        contentValue.put(DataBaseHelper.RESULT1,res1);
        contentValue.put(DataBaseHelper.RESULT2,res2);
        contentValue.put(DataBaseHelper.TYPE,type1);
        database.insert(DataBaseHelper.TABLE_NAME, null, contentValue);
    }*/

    private Cursor fetch(){
        String[] columns = new String[] { DataBaseHelper._ID,
        DataBaseHelper.A,
        DataBaseHelper.B,
        DataBaseHelper.C,
        DataBaseHelper.RESULT1,
        DataBaseHelper.RESULT2,
        DataBaseHelper.TYPE};

        Cursor cursor = database.query(DataBaseHelper.TABLE_NAME, columns, null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    /*private int update(long _id, String a1, String b1, String c1, String res1, String res2, String type1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.A, a1);
        contentValues.put(DataBaseHelper.B, b1);
        contentValues.put(DataBaseHelper.C, c1);
        contentValues.put(DataBaseHelper.RESULT1, res1);
        contentValues.put(DataBaseHelper.RESULT2, res2);
        contentValues.put(DataBaseHelper.TYPE, type1);
        int i = database.update(DataBaseHelper.TABLE_NAME, contentValues, DataBaseHelper._ID + " = " + _id, null);
        return i;
    } */

    /*private void delete(long _id){
        database.delete(DataBaseHelper.TABLE_NAME,DataBaseHelper._ID + "=" + _id,null);
    }*/

    public void insert(HistoryItem item){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.A, item.getOperandA());
        contentValue.put(DataBaseHelper.B, item.getOperandB());
        contentValue.put(DataBaseHelper.C, item.getOperandC());
        contentValue.put(DataBaseHelper.RESULT1, item.getOperandRes1());
        contentValue.put(DataBaseHelper.RESULT2, item.getOperandRes2());
        contentValue.put(DataBaseHelper.TYPE, item.getOperandType());
        database.insert(DataBaseHelper.TABLE_NAME,null,contentValue);
    }

    public void deleteAll() { database.delete(DataBaseHelper.TABLE_NAME, "", null);}


    public String getAllAsText(){
        String text = "";
        Cursor cursor = fetch();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String a1, b1, c1, res1, res2, type1;
            a1 = cursor.getString(cursor.getColumnIndex(DataBaseHelper.A));
            b1 = cursor.getString(cursor.getColumnIndex(DataBaseHelper.B));
            c1 = cursor.getString(cursor.getColumnIndex(DataBaseHelper.C));
            res1 = cursor.getString(cursor.getColumnIndex(DataBaseHelper.RESULT1));
            res2 = cursor.getString(cursor.getColumnIndex(DataBaseHelper.RESULT2));
            type1 = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TYPE));
            HistoryItem item = new HistoryItem(a1,b1,c1,res1,res2,type1);
            text += (item.getTextRepresentation() + "\n");
            cursor.moveToNext();
        }
        return text;
    }

}
