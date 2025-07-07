package com.aquirez.tarefafinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aquirez.tarefafinal.entidade.livro;

import java.util.ArrayList;
import java.util.List;

public class livroDB {
    private database helper;

    public livroDB(Context ctx) { helper = new database(ctx); }

    public long inserir(livro l) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo", l.getTitulo());
        cv.put("autor", l.getAutor());
        cv.put("capaUri", l.getCapaUri());
        return db.insert("livros", null, cv);
    }

    public int atualizar(livro l) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo", l.getTitulo());
        cv.put("autor", l.getAutor());
        cv.put("capaUri", l.getCapaUri());
        return db.update("livros", cv, "id=?", new String[]{String.valueOf(l.getId())});
    }

    public int excluir(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.delete("livros", "id=?", new String[]{String.valueOf(id)});
    }

    public livro buscarPorId(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("livros", null, "id=?", new String[]{String.valueOf(id)}, null,null,null);
        if (c.moveToFirst()) {
            livro l = new livro();
            l.setId(c.getLong(c.getColumnIndex("id")));
            l.setTitulo(c.getString(c.getColumnIndex("titulo")));
            l.setAutor(c.getString(c.getColumnIndex("autor")));
            l.setCapaUri(c.getString(c.getColumnIndex("capaUri")));
            c.close();
            return l;
        }
        c.close();
        return null;
    }

    public List<livro> listarTodos() {
        List<livro> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("livros", null, null, null, null, null, "titulo ASC");
        while (c.moveToNext()) {
            livro l = new livro();
            l.setId(c.getLong(c.getColumnIndex("id")));
            l.setTitulo(c.getString(c.getColumnIndex("titulo")));
            l.setAutor(c.getString(c.getColumnIndex("autor")));
            l.setCapaUri(c.getString(c.getColumnIndex("capaUri")));
            lista.add(l);
        }
        c.close();
        return lista;
    }
}