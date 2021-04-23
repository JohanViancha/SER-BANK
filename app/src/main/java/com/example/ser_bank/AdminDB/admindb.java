package com.example.ser_bank.AdminDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class admindb extends SQLiteOpenHelper {


    public admindb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (" +
                "id_usuario integer primary key," +
                "nombre_usu text," +
                "apellidos_usu text," +
                "email_usu text," +
                "password_usu text," +
                "tipo_usu text" +
                ")");

        db.execSQL("CREATE TABLE cuenta(" +
                "id_cuenta integer primary key," +
                "id_usuario integer," +
                "codigo_cue text," +
                "saldo_cue money," +
                "tipo_cue text," +
                "foreign key (id_usuario) references usuario (id_usuario))");

        db.execSQL("CREATE TABLE transaccion(" +
                "id_transacion integer primary key," +
                "id_cue_emi integer," +
                "id_cue_rec integer," +
                "tipo_trans text," +
                "monto_trans money," +
                "fecha_trans date," +
                "foreign key (id_cue_emi) references cuenta (id_cuenta)," +
                "foreign key (id_cue_rec) references cuenta (id_cuenta))");


        db.execSQL("INSERT INTO USUARIO VALUES (1,'Johan','Viancha','vianchajohan@gmail.com','123','Administrador')");
        db.execSQL("INSERT INTO CUENTA VALUES (1,1,'1234567',10000,'Ahorros')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
