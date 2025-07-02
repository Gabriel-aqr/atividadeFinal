package com.aquirez.tarefafinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import controller.Controller;

public class MainActivity extends AppCompatActivity {

    private Button entrar, cadastrar;
    private EditText usuario, senha;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            prefs = getSharedPreferences("dados-login", MODE_PRIVATE);
            entrar = findViewById(R.id.entrarId);
            cadastrar = findViewById(R.id.cadastrarId);
            usuario = findViewById(R.id.userId);
            senha = findViewById(R.id.senhaId);
            String usuarioS = prefs.getString("nome", "Nome");
            String senhaS = prefs.getString("senha", "Senha");
            String status = prefs.getString("status", "Status");
            usuario.setText(usuarioS);
            senha.setText(senhaS);

            if (status.equals("logado")) {
                Intent intent=new Intent(this,Home.class);
                startActivity(intent);
            }

            usuario.setOnClickListener((View view) ->{
                usuario.setText("");
            });
            senha.setOnClickListener((View view) ->{
                senha.setText("");
            });
            return insets;
        });

    }

    public void btn_entrar(View view) {
        Controller.Salvar(this,usuario.getText().toString(),senha.getText().toString());
    }

    public void btn_cadastrar(View view) {
        Controller Controller = new Controller();
        Controller.Salvar(this,usuario.getText().toString(),senha.getText().toString());
    }


}