package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ser_bank.AdminDB.adminUsuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ser_bank.R;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextInputEditText usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usuario = findViewById(R.id.txtusuario);
        this.password = findViewById(R.id.txtpassword);
    }

    public void ingresar(View view){
        String usuario =  this.usuario.getText().toString();
        String password = this.password.getText().toString();

        adminUsuario adminusu = new adminUsuario();
        if(!usuario.isEmpty() && !password.isEmpty()){

            Cursor result =  adminusu.ingresar(this,usuario, password);

            if(result != null){
                SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("id_usuario", result.getInt(0));
                editor.putString("tipo_usuario", result.getString(3));
                editor.putInt("id_cuenta", result.getInt(4));
                editor.putString("nombre", result.getString(1));
                editor.putString("apellido", result.getString(2));
                editor.putFloat("saldo", result.getFloat(6));
                editor.commit();

                System.out.println(result.getString(3));
                if(result.getString(3).equals("Administrador")){

                    Intent inte2 = new Intent(this, Menu.class);
                    startActivity(inte2);

                }else{
                    Intent inte1 = new Intent(this, Menu2.class);
                    startActivity(inte1);
                }

            }
            else{


                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();

            }
        }


    }
}