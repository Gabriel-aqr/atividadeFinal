package com.aquirez.tarefafinal.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import com.aquirez.tarefafinal.database.database;

public class Controller {
    private database helper;

    public Controller(Context context) {
        helper = new database(context);
    }

    public boolean cadastrar(String u, String s) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("usuario", u);
        cv.put("senha", s);
        long id = db.insert("users", null, cv);
        return id > 0;
    }

    public boolean login(String u, String s) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("users", null, "usuario=? AND senha=?", new String[]{u,s}, null,null,null);
        boolean ok = c.moveToFirst();
        c.close();
        return ok;
    }
}