package com.aquirez.tarefafinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String DB_NAME = "app.db";
    public static final int VERSAO = 1;

    public database(Context c) {
        super(c, DB_NAME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT,usuario TEXT UNIQUE,senha TEXT);");
        db.execSQL("CREATE TABLE livros(id INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT,autor TEXT,capaUri TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v0, int v1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS livros");
        onCreate(db);
    }
}