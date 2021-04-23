package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.ser_bank.AdminDB.*;
import com.example.ser_bank.Models.Transaccion;
import com.google.android.material.textfield.TextInputEditText;

public class RealizarTransaccion extends AppCompatActivity {

    TextInputEditText cuenta,monto;
    AutoCompleteTextView tipotransaccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_transaccion);

        tipotransaccion = findViewById(R.id.txttipotransaccion_reatra);
        cuenta = findViewById(R.id.txtcuenta_reatra);
        monto = findViewById(R.id.txtmonto_reatra);
    }


    public void realizarTransacion(View view) {

        String tipotransaccion = this.tipotransaccion.getText().toString();
        String cuenta = this.cuenta.getText().toString();
        double monto = (Double) Double.parseDouble(this.monto.getText().toString());

        //Se valida que la cuenta ingresada exista
        adminCuenta admincuenta = new adminCuenta();
        int id_cuenta_rec = admincuenta.validarCuenta(this, cuenta);

        adminTransaccion admintra = new adminTransaccion();

        // Se obtiene el id_cuenta del usuario que se encuentra logeado
        SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
        int id_cuenta_emi = sharedpreferences.getInt("id_cuenta", -1);

        Transaccion transaccion = new Transaccion(tipotransaccion, monto, id_cuenta_emi, id_cuenta_rec);

        if(admintra.realizarTransaccion(this, transaccion)){
            Toast.makeText(this, "La transacción ha sido exitosa", Toast.LENGTH_SHORT).show();

        }




    }


}