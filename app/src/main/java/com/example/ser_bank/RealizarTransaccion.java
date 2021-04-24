package com.example.ser_bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ser_bank.AdminDB.*;
import com.example.ser_bank.Models.Transaccion;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RealizarTransaccion extends AppCompatActivity {

    TextInputEditText cuenta,monto;
    AutoCompleteTextView medio;
    TextInputLayout layoutCuenta, LayoutMedio;
    TextView titlemedio;
    String tipo  = "Retiro";

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    boolean respuesta = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_transaccion);

        cuenta = findViewById(R.id.txtcuenta_reatra);
        monto = findViewById(R.id.txtmonto_reatra);
        medio = findViewById(R.id.txtmedio);
        titlemedio = findViewById(R.id.txttitle_medio);
        layoutCuenta = (TextInputLayout) findViewById(R.id.txtlayout_cuenta);
        LayoutMedio = (TextInputLayout) findViewById(R.id.txtlen);


        String transaccion = getIntent().getExtras().getString("transaccion");

        if(transaccion.equals("retirar")){
            retirar();
        }
        else{
            transferir();
        }




    }



    public void realizarTransacion(View view) {

        String cuenta = this.cuenta.getText().toString();
        double monto = (Double) Double.parseDouble(this.monto.getText().toString());

        //Se valida que la cuenta ingresada exista
        adminCuenta admincuenta = new adminCuenta();
        adminTransaccion admintra = new adminTransaccion();



        SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
        int id_cuenta_emi = sharedpreferences.getInt("id_cuenta", 0);


        if (tipo != "Retiro") {
            Cursor cursor = admincuenta.validarCuenta(this, cuenta);
            if (cursor != null) {
                setRespuesta(admincuenta.transferir(this, id_cuenta_emi, cursor.getInt(2), monto));
                registrarTranferencia(cursor,admintra,0);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¡Error!");
            builder.setMessage("La cuenta ingresada no existe")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Se cobrará 2000 pesos por el retiro")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setRespuesta(admincuenta.retirar(builder.getContext(), id_cuenta_emi, monto));
                            registrarTranferencia(null, admintra,2000);
                        }
                    })
                    .setNegativeButton("Denegar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();


    }




    }

    public  void retirar(){
        titlemedio.setVisibility(View.VISIBLE);
        layoutCuenta.setVisibility(View.INVISIBLE);
        LayoutMedio.setVisibility(View.VISIBLE);
        tipo = "Retiro";
        String[] medioarr = {"Cajero", "Corresponsal"};

        ArrayAdapter<String> arraymedioadapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,medioarr);
        medio.setAdapter(arraymedioadapter);
    }

    public void transferir(){
        titlemedio.setVisibility(View.INVISIBLE);
        layoutCuenta.setVisibility(View.VISIBLE);
        LayoutMedio.setVisibility(View.INVISIBLE);
        tipo = "Transferencia";
    }

    public void registrarTranferencia(Cursor cursor,adminTransaccion admintra, float commision){

        double monto = (Double) Double.parseDouble(this.monto.getText().toString());

        SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
        String nombre = sharedpreferences.getString("nombre", "");
        String apellido = sharedpreferences.getString("apellido", "");
        float saldoactual = sharedpreferences.getFloat("saldo",0)- ((float)monto+commision);

        if (isRespuesta()) {
            String nombre_rec = "";
            if(cursor == null){
                nombre_rec = medio.getText().toString();
            }
            else{
                nombre_rec = cursor.getString(0) + cursor.getString(1);
            }

            long ahora = System.currentTimeMillis();
            Date fecha = new Date(ahora);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String actual = df.format(fecha);
            Transaccion transaccion = new Transaccion(tipo, monto, nombre + apellido, nombre_rec, actual);

            if (admintra.realizarTransaccion(this, transaccion)) {


                Toast.makeText(this, "La transacción ha sido exitosa", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putFloat("saldo",saldoactual);
                editor.apply();

                Intent inte = new Intent(this, Menu2.class);
                startActivity(inte);

            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¡Error!");
            builder.setMessage("El valor ingresado excede el saldo en la cuenta")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }

    }


}