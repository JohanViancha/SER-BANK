package com.example.ser_bank.AdminDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ser_bank.Models.Transaccion;

public class adminTransaccion {


    public boolean realizarTransaccion(Context context, Transaccion transaccion){

        boolean respuesta = false;

        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();
        ContentValues transaccion_nueva = new ContentValues();
        try{
            transaccion_nueva.put("tipo_trans", transaccion.getTipo());
            transaccion_nueva.put("monto_trans", transaccion.getMonto());
            transaccion_nueva.put("id_cue_emi", transaccion.getId_cue_emi());
            transaccion_nueva.put("id_cue_rec", transaccion.getId_cue_rec());
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
        Cursor fila = sql.rawQuery("select * from transaccion tra inner join cuenta cue" +
                " on tra.id_cue_emi = cue.id_cuenta  inner join usuario usu " +
                "on usu.id_usuario = cue.id_usuario" ,null);

        if(fila.getCount() != 0){
            return fila;
        }
        else{
            return null;
        }

    }



}
