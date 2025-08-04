package com.aquirez.tarefafinal.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.widget.Toast;

import com.aquirez.tarefafinal.database.database;

public class Controller {
    private database DBcontroller;

    public Controller(Context context) {
        DBcontroller = new database(context);
    }

    public boolean cadastrar(String u, String s) {
        SQLiteDatabase db = DBcontroller.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", u);
        contentValues.put("senha", s);
        long id = db.insert("users", null, contentValues);
        return id > 0;
    }

    public boolean login(String u, String s) {
        SQLiteDatabase db = DBcontroller.getReadableDatabase();
        Cursor cursor = db.query("users", null, "usuario=? AND senha=?", new String[]{u,s}, null,null,null);
        boolean ok = cursor.moveToFirst();
        cursor.close();
        return ok;
    }

    public void salvar(Context context, String usuario, String senha) {
        SharedPreferences pref = context.getSharedPreferences("Lista_Users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("usuario", usuario);
        editor.putString("senha", senha);
        editor.apply();
        Toast.makeText(context.getApplicationContext(), "Salvo.", Toast.LENGTH_SHORT).show();
    }
}