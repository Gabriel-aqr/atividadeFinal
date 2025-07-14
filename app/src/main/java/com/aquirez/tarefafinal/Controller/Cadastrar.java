package com.aquirez.tarefafinal.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.aquirez.tarefafinal.MainActivity;
import com.aquirez.tarefafinal.R;

public class Cadastrar extends AppCompatActivity {
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_home);
    }
    private EditText usuario, senha, senhaConfirmacao;
    private Controller controller;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        usuario = findViewById(R.id.userIdd);
        senha = findViewById(R.id.senhaIdd);
        senhaConfirmacao = findViewById(R.id.confirmarSenha);
        controller = new Controller(this);
    }
    public void btnRegistrar(View view) {
        String u = usuario.getText().toString();
        String s = senha.getText().toString();
        String s2 = senhaConfirmacao.getText().toString();
        if (s.equals(s2)) {
            Toast.makeText(this, "Cadastro realizado.", Toast.LENGTH_SHORT).show();
            controller.cadastrar(u, s);
            controller.login(u, s);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (!s.equals(s2)) {
            Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Já existe uma conta com esses credenciais.", Toast.LENGTH_SHORT).show();
        }
    }
}