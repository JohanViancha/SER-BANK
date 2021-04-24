package com.example.ser_bank.AdminDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ser_bank.Models.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class adminTransaccion {


    public boolean realizarTransaccion(Context context, Transaccion transaccion){

        boolean respuesta = false;

        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();
        ContentValues transaccion_nueva = new ContentValues();
        try{
            transaccion_nueva.put("tipo_trans", transaccion.getTipo());
            transaccion_nueva.put("monto_trans", transaccion.getMonto());
            transaccion_nueva.put("nom_emi", transaccion.getNombre_emi());
            transaccion_nueva.put("nom_rec", transaccion.getNombre_rec());
            transaccion_nueva.put("fecha_trans", transaccion.getFecha());

            if(sql.insert("transaccion",null, transaccion_nueva) != 0){

                respuesta = true;
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

        return respuesta;
    }

    public Cursor listarTransacciones(Context context){
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getReadableDatabase();
        Cursor filas = sql.rawQuery("select * from transaccion" ,null);

        if(filas.getCount() != 0){
            return filas;
        }
        else{
            return null;
        }

    }



}
