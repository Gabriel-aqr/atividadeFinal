package com.aquirez.tarefafinal.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import com.aquirez.tarefafinal.database.database;

public class Controller {
    private database DBhelper;

    public Controller(Context context) {
        DBhelper = new database(context);
    }

    public boolean cadastrar(String u, String s) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", u);
        contentValues.put("senha", s);
        long id = db.insert("users", null, contentValues);
        return id > 0;
    }

    public boolean login(String u, String s) {
        SQLiteDatabase db = DBhelper.getReadableDatabase();
        Cursor cursor = db.query("users", null, "usuario=? AND senha=?", new String[]{u,s}, null,null,null);
        boolean ok = cursor.moveToFirst();
        cursor.close();
        return ok;
    }
}