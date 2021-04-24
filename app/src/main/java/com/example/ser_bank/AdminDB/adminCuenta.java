package com.example.ser_bank.AdminDB;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
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


    public Cursor validarCuenta(Context context, String cuenta){
        Cursor respuesta = null;
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getReadableDatabase();
        Cursor fila = sql.rawQuery("select usu.nombre_usu, usu.apellidos_usu, cue.id_cuenta from cuenta cue inner join usuario usu " +
                "on usu.id_usuario = cue.id_usuario where codigo_cue = \'"+cuenta+"\'",null);

        if(fila.moveToFirst()){
            respuesta = fila;
        }

        return respuesta;

    }

    public boolean transferir(Context context, int cuenta_emi,int cuenta_rec, double valor){
        boolean respuesta = false;
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();

        if(validarMonto(context,valor,cuenta_emi)){
            sql.execSQL("update cuenta set saldo_cue = saldo_cue + \'"+valor+"\' where id_cuenta = \'"+cuenta_rec+"\'");
            sql.execSQL("update cuenta set saldo_cue = saldo_cue - \'" + valor + "\' where id_cuenta = \'"+cuenta_emi+"\'");
            //cursor1.moveToFirst();
            respuesta = true;
        }

        return respuesta;
    }

    public boolean retirar(Context context, int cuenta, double valor){
        boolean respuesta = false;
        double comision = 2000;
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();

        if(validarMonto(context,valor+comision,cuenta)){
            valor = valor+comision;
            sql.execSQL("update cuenta set saldo_cue = saldo_cue -\'" +valor+ "\' where id_cuenta = \'" + cuenta + "\'");
            respuesta = true;
        }

        return  respuesta;
    }


    public boolean validarMonto(Context context, double valor, int cuenta){

        boolean respuesta = false;
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getReadableDatabase();
        Cursor fila = sql.rawQuery("select saldo_cue from cuenta where id_cuenta = \'"+cuenta+"\'",null);

        fila.moveToFirst();
        double saldo = fila.getDouble(0);

        if(saldo >= valor){
            respuesta = true;
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
