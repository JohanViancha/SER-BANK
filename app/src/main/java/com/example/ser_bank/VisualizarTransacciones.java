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
import com.example.ser_bank.RecyclerView.ListAdapterTransaccion;
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

        Cursor filas = admintra.listarTransacciones(this);

        if(filas != null){

            while (filas.moveToNext()){
                Transaccion transaccion = new Transaccion();
                transaccion.setNombre_emi(filas.getString(1));
                transaccion.setNombre_rec(filas.getString(2));
                transaccion.setMonto(filas.getDouble(4));
                transaccion.setTipo(filas.getString(3));
                arraytransacciones.add(transaccion);
            }
        }
        else{

            Toast.makeText(this, "No hay transacciones", Toast.LENGTH_SHORT).show();
        }

        ListAdapterTransaccion adapter = new ListAdapterTransaccion(arraytransacciones,this);

        RecyclerView recycler = findViewById(R.id.rc_listransacciones);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }
}