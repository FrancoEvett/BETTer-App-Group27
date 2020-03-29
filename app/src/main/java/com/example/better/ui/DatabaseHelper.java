package com.example.better.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import okhttp3.internal.Util;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Labs_table";
    private static final String COL1 = "Name";
//    private static final String COL2 = "Labs";

    @Override
    public void onUpgrade (SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT)";
        db.execSQL(createTable);
    }
    public DatabaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }

    public boolean addData(String labnumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, labnumber);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false; //data not added
        }
        else{
            return true; //data added succesfully
        }
    }

    public void resetTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

    public ArrayList<String> getData(){
        ArrayList<String> labsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if(cursor.moveToFirst()){
            do{
                labsList.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        return labsList;
    }

}
