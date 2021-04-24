package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.ser_bank.AdminDB.*;
import com.example.ser_bank.Models.Transaccion;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RealizarTransaccion extends AppCompatActivity {

    TextInputEditText cuenta,monto;
    AutoCompleteTextView tipotransaccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_transaccion);

        tipotransaccion = findViewById(R.id.txtautocomplete);
        cuenta = findViewById(R.id.txtcuenta_reatra);
        monto = findViewById(R.id.txtmonto_reatra);

        String[] tipos = {"Retiro", "Transferencia"};
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,tipos);
        tipotransaccion.setAdapter(arrayadapter);
    }


    public void realizarTransacion(View view) {

        String tipotransaccion = this.tipotransaccion.getText().toString();
        String cuenta = this.cuenta.getText().toString();
        double monto = (Double) Double.parseDouble(this.monto.getText().toString());

        //Se valida que la cuenta ingresada exista
        adminCuenta admincuenta = new adminCuenta();
        Cursor cursor = admincuenta.validarCuenta(this, cuenta);
        String nombre_rec = "";
        if(cursor != null){
           nombre_rec = cursor.getString(0) + cursor.getString(1);
        }
        else{
            Toast.makeText(this, "El numero de cuenta ingresado no existe", Toast.LENGTH_SHORT).show();
        }

        adminTransaccion admintra = new adminTransaccion();

        // Se obtiene el id_cuenta del usuario que se encuentra logeado
        SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
        String nombre = sharedpreferences.getString("nombre", "");
        String apellido = sharedpreferences.getString("apellido", "");

        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String actual = df.format(fecha);
        Transaccion transaccion = new Transaccion(tipotransaccion, monto, nombre+apellido, nombre_rec,actual);

        if(admintra.realizarTransaccion(this, transaccion)){
            Toast.makeText(this, "La transacción ha sido exitosa", Toast.LENGTH_SHORT).show();

        }




    }


}