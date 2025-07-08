package com.aquirez.tarefafinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.aquirez.tarefafinal.database.livroDB;
import com.aquirez.tarefafinal.entidade.livro;

import java.io.File;
import java.io.FileOutputStream;

public class BookInsert extends AppCompatActivity {
    private static final int REQ_CAMERA = 100;
    private EditText etTitulo, etAutor;
    private ImageView ivCapa;
    private Button btnSalvar, btnExcluir, btnCapa;
    private livroDB ldb;
    private livro atual;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.livro_form);
        ldb = new livroDB(this);

        etTitulo = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etAutor);
        ivCapa = findViewById(R.id.ivCapa);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnCapa = findViewById(R.id.btnCapa);

        long id = getIntent().getLongExtra("livro_id", -1);
        if (id != -1) {
            atual = ldb.buscarPorId(id);
            etTitulo.setText(atual.getTitulo());
            etAutor.setText(atual.getAutor());
            ivCapa.setImageURI(Uri.parse(atual.getCapaUri()));
            btnExcluir.setOnClickListener(v -> {
                ldb.excluir(atual.getId());
                finish();
            });
        }

        btnCapa.setOnClickListener(v -> {
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQ_CAMERA);

        });

        btnSalvar.setOnClickListener(v -> {
            String t = etTitulo.getText().toString();
            String a = etAutor.getText().toString();
            if (t.isEmpty() || a.isEmpty()) {
                Toast.makeText(this, "Preencha todos campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if (atual == null) atual = new livro();
            atual.setTitulo(t);
            atual.setAutor(a);
            if (ivCapa.getTag() != null) {
                atual.setCapaUri(ivCapa.getTag().toString());
            }
            if (atual.getId() == 0) ldb.inserir(atual);
            else ldb.atualizar(atual);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int rq, int res, Intent data) {
        super.onActivityResult(rq, res, data);
        if (rq == REQ_CAMERA && res == Activity.RESULT_OK) {
            Bundle b = data.getExtras();
            Bitmap bmp = (Bitmap) b.get("data");
            try {
                File file = new File(getFilesDir(), "capa_" + System.currentTimeMillis() + ".jpg");
                FileOutputStream fos = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.JPEG, 80, fos);
                fos.close();
                Uri uri = Uri.fromFile(file);
                ivCapa.setImageURI(uri);
                ivCapa.setTag(uri.toString());
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}