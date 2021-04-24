package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu2 extends AppCompatActivity {

    TextView nombre, saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        saldo = findViewById(R.id.txtsald_ug);
        nombre = findViewById(R.id.txtnombre_ug);
        SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
        nombre.setText(sharedpreferences.getString("nombre", "")+ " " +sharedpreferences.getString("apellido", ""));
        saldo.setText("$ " + String.valueOf(sharedpreferences.getFloat("saldo", 0)));

    }

    public void retirar(View view){
        Intent inte = new Intent(this, RealizarTransaccion.class);
        inte.putExtra("transaccion", "retirar");
        startActivity(inte);
    }

    public void tranferir(View view){
        Intent inte2 = new Intent(this, RealizarTransaccion.class);
        inte2.putExtra("transaccion", "transferir");
        startActivity(inte2);

    }

    public void cerrarSesion(View view){
        Intent inte3 = new Intent(this, Login.class);
        startActivity(inte3);
    }
}