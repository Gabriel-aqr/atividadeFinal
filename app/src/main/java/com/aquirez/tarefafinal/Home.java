package com.aquirez.tarefafinal;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aquirez.tarefafinal.Controller.LivroAdapter;
import com.aquirez.tarefafinal.database.livroDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LivroAdapter adapter;
    private livroDB livroDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerLivros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        livroDAO = new livroDB(this);
        adapter = new LivroAdapter(livroDAO.listarTodos());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(livro -> {
            Intent it = new Intent(this, BookInsert.class);
            it.putExtra("livro_id", livro.getId());
            startActivity(it);
        });

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> startActivity(new Intent(this, BookInsert.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setLivros(livroDAO.listarTodos());
    }
}