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


            if(sql.insert("transaccion",null, transaccion_nueva) != 0){

                respuesta = true;
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

        return respuesta;
    }



}
