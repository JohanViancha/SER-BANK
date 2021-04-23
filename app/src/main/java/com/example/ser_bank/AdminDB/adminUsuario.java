package com.example.ser_bank.AdminDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ser_bank.Models.Cuenta;
import com.example.ser_bank.Models.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class adminUsuario {


    public Cursor ingresar(Context context, String usuario, String password){
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getReadableDatabase();
        Cursor fila = sql.rawQuery("select usu.id_usuario, usu.nombre_usu, usu.apellidos_usu,usu.tipo_usu, " +
                "cue.codigo_cue, cue.saldo_cue, cue.tipo_cue" +
                " from usuario usu inner join cuenta cue on usu.id_usuario = cue.id_usuario" +
                " where email_usu=\'"+usuario+"\' and password_usu = \'"+password+"\'" ,null);

        if(fila.moveToFirst()){
            return fila;
        }
        else{
            return null;
        }

    }

    public boolean crearUsuario(Context context, Usuario usuario, Cuenta cuenta){
        boolean respuesta = false;
        final String tipousuario = "Usuario general";
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getWritableDatabase();
        ContentValues usuario_nuevo = new ContentValues();
        try{
            usuario_nuevo.put("nombre_usu", usuario.getNombre());
            usuario_nuevo.put("apellidos_usu", usuario.getApellidos());
            usuario_nuevo.put("email_usu", usuario.getEmail());
            usuario_nuevo.put("password_usu", usuario.getPassword());
            usuario_nuevo.put("tipo_usu",tipousuario);

            if(sql.insert("usuario",null, usuario_nuevo) != 0){

                //Obtengo el id del usuario que se acaba de registrar
                usuario.setId(obtenerIdUsuario(context));

                adminCuenta nueva_cuenta = new adminCuenta();

                if(nueva_cuenta.crearCuenta(context, usuario,cuenta)){
                    respuesta = true;
                }
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

        return respuesta;
    }


    private int obtenerIdUsuario(Context context){
        admindb admin = new admindb(context, "SER-BANK",null,1);
        SQLiteDatabase sql = admin.getReadableDatabase();
        Cursor fila = sql.rawQuery("select id_usuario from usuario ORDER BY ID_USUARIO DESC LIMIT 1" ,null);

        fila.moveToFirst();
        int idusuario = fila.getInt(0);
        return idusuario;
    }

}
