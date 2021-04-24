package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void crearUsuario(View view){

        Intent inte = new Intent(this, CrearUsuario.class);
        startActivity(inte);
    }

    public void realizarTransaccion(View view){
        Intent inte = new Intent(this, RealizarTransaccion.class);
        startActivity(inte);
    }

    public void listarUsuarios(View view){
        Intent inte = new Intent(this, VisualizarUsuarios.class);
        startActivity(inte);
    }

    public void listarTransacciones(View view){
        Intent inte = new Intent(this, VisualizarTransacciones.class);
        startActivity(inte);
    }
}