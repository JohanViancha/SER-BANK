package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ser_bank.AdminDB.adminTransaccion;
import com.example.ser_bank.Models.SaldoUsuario;
import com.example.ser_bank.Models.Transaccion;
import com.example.ser_bank.RecyclerView.ListAdapterUsuarios;

import java.util.ArrayList;
import java.util.List;

public class VisualizarTransacciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_transacciones);

        ListarTransacciones();
    }

    private void ListarTransacciones(){

        adminTransaccion admintra = new adminTransaccion();
        List<Transaccion> arraytransacciones= new ArrayList<Transaccion>();

        Cursor fila = admintra.listarTransacciones(this);

        /*if(fila != null){

            while (fila.moveToNext()){
                Transaccion transaccion = new Transaccion();
                transaccion.setNombre_emi(fila.getString(0));
                transaccion.setApellido(fila.getString(1));
                transaccion.setSaldo(fila.getDouble(2));
                arraytransacciones.add(saldousuario);
            }
        }
        else{

            Toast.makeText(this, "No hay transacciones", Toast.LENGTH_SHORT).show();
        }*/

       /* ListAdapterUsuarios adapter = new ListAdapterUsuarios(arrayusuario,this);

        RecyclerView recycler = findViewById(R.id.rc_listusuarios);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);*/

    }
}