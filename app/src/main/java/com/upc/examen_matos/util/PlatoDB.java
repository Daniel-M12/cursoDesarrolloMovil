package com.upc.examen_matos.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlatoDB extends SQLiteOpenHelper {

    public PlatoDB (Context context){
        super(context, "platos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE platos " +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nombre TEXT NOT NULL, " +
                " categoria TEXT NOT NULL, " +
                " observaciones TEXT NOT NULL, " +
                " precio DOUBLE NOT NULL, " +
                " cantidad INTEGER NOT NULL, " +
                " fechaPedido TEXT NOT NULL);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
