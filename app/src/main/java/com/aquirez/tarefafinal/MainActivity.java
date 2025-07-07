package com.aquirez.tarefafinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.aquirez.tarefafinal.Controller.Controller;

public class MainActivity extends AppCompatActivity {
    private EditText usuario, senha;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.userId);
        senha = findViewById(R.id.senhaId);
        controller = new Controller(this);
    }

    public void btnEntrar(View view) {
        String u = usuario.getText().toString();
        String s = senha.getText().toString();
        if (u.isEmpty() || s.isEmpty()) {
            Toast.makeText(this, "Preencha usuário e senha", Toast.LENGTH_SHORT).show();
            return;
        }
        if (controller.login(u, s)) {
            startActivity(new Intent(this, Home.class));
            finish();
        } else {
            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnCadastrar(View view) {
        String u = usuario.getText().toString();
        String s = senha.getText().toString();
        if (controller.cadastrar(u, s)) {
            Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro no cadastro", Toast.LENGTH_SHORT).show();
        }
    }
}