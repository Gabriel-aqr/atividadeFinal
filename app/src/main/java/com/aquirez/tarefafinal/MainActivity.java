package com.aquirez.tarefafinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.aquirez.tarefafinal.Controller.Cadastrar;
import com.aquirez.tarefafinal.Controller.Controller;

public class MainActivity extends AppCompatActivity {
    private EditText usuario, senha, usuarioL, senhaL;
    SharedPreferences prefs;
    private Controller controller;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            usuario = findViewById(R.id.userId);
            senha = findViewById(R.id.senhaId);
            controller = new Controller(this);

            prefs = getSharedPreferences("Lista_Users", MODE_PRIVATE);
            String usuarioS = prefs.getString("usuario", "usuario");
            String senhaS = prefs.getString("senha", "senha");
            usuarioL.setText(usuarioS);
            senhaL.setText(senhaS);
            if (controller.login(usuarioS, senhaS)) {
                startActivity(new Intent(this, Home.class));
                controller.salvar(this,usuarioL.getText().toString(),senhaL.getText().toString());
                finish();
            }
            return insets;
        });
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
            controller.salvar(this,usuarioL.getText().toString(),senhaL.getText().toString());
            finish();
        } else {
            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
        }
    }
    public void btnCadastrar(View view) {
        startActivity(new Intent(this, Cadastrar.class));
        finish();
    }
}