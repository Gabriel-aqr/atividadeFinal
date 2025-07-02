package controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Controller {

    public static void Salvar(Context context, String usuario, String senha) {
        SharedPreferences pref = context.getSharedPreferences("dados-login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("usuario", usuario);
        editor.putString("senha", senha);
        editor.apply();
        Toast.makeText(context.getApplicationContext(), "Salvo.", Toast.LENGTH_SHORT).show();
    }
}
