package com.example.ser_bank.AdminDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ser_bank.Models.Cuenta;
import com.example.ser_bank.Models.Usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class adminCuenta {


    public boolean crearCuenta(Context context,Usuario usuario, Cuenta cuenta){

        boolean respuesta = false;
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();
        ContentValues cuenta_nueva = new ContentValues();
        try {
            cuenta_nueva.put("codigo_cue", generarCuenta(usuario.getNombre(), usuario.getApellidos()));
            cuenta_nueva.put("id_usuario", usuario.getId());
            cuenta_nueva.put("saldo_cue", cuenta.getSaldo());
            cuenta_nueva.put("tipo_cue", cuenta.getTipo());

            if(sql.insert("cuenta", null, cuenta_nueva) != 0){
                respuesta = true;
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

       return respuesta;
    }


    public int validarCuenta(Context context, String cuenta){
        int respuesta = 0;
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();
        Cursor fila = sql.rawQuery("select id_cuenta from cuenta where codigo_cue = \'"+cuenta+"\'" ,null);

        if(fila.moveToFirst()){
            respuesta = fila.getInt(0);
        }

        return respuesta;

    }


    private String generarCuenta(String nombre, String apellidos){
        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("ddMMyy");
        String actual = df.format(fecha);
        String codigo = String.valueOf(nombre.charAt(0)) + String.valueOf(apellidos.charAt(0)) + actual;
        return codigo;
    }
}