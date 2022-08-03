package com.myapp.lovetest8121988;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.DB.ItemTruyen;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class TruyenChuaGS extends HandleDB {
    private static final String TABLE = "COMPANY";
    private static final String NUM = "Num";
    private static volatile TruyenChuaGS INSTANCE;

    private TruyenChuaGS(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static TruyenChuaGS getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (TruyenChuaGS.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TruyenChuaGS(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public ArrayList<ItemTruyen> getArrayItem() {
        ArrayList<ItemTruyen> arr  = new ArrayList<>();
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String ret = cursor.getString(1);
                ItemTruyen ret2 = new ItemTruyen(ret,"god1");
                arr.add(ret2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return arr;
    }

    public String getContent(String item, int index) {
        String ret = null;
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE + " WHERE " + NUM + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{item});

        if (cursor.moveToFirst()) {
            do {
                ret = cursor.getString(index);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return ret;
    }
}
