package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ser_bank.AdminDB.*;
import com.example.ser_bank.Models.SaldoUsuario;
import com.example.ser_bank.RecyclerView.ListAdapterUsuarios;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class VisualizarUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_usuarios);

        ListarUsuarios();
    }

    private void ListarUsuarios(){

        adminUsuario adminusu = new adminUsuario();
        List<SaldoUsuario> arrayusuario = new ArrayList<SaldoUsuario>();

        Cursor fila = adminusu.listarUsuarios(this);

        if(fila.getCount() != 0){

            while (fila.moveToNext()){
                SaldoUsuario saldousuario = new SaldoUsuario();
                saldousuario.setNombre(fila.getString(0));
                saldousuario.setApellido(fila.getString(1));
                saldousuario.setSaldo(fila.getDouble(2));
                arrayusuario.add(saldousuario);
            }
        }

        ListAdapterUsuarios adapter = new ListAdapterUsuarios(arrayusuario,this);

        RecyclerView recycler = findViewById(R.id.rc_listusuarios);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
}