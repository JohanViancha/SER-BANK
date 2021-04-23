package com.example.ser_bank;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ser_bank.AdminDB.adminUsuario;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ser_bank.Models.Cuenta;
import com.example.ser_bank.Models.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class CrearUsuario extends AppCompatActivity {

    TextInputEditText nombre, apellidos, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        nombre = findViewById(R.id.txtnombre_creusu);
        apellidos = findViewById(R.id.txtapellidos_creusu);
        email = findViewById(R.id.txtemail_creusu);
        password = findViewById(R.id.txtcuenta_reatra);

    }

    public void crearUsuario(View view){

        String nombre = this.nombre.getText().toString();
        String apellidos = this.apellidos.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        Usuario usuario = new Usuario(nombre,apellidos,email,password);
        Cuenta cuenta = new Cuenta(100000,"Ahorros");

        adminUsuario nuevo_usuario = new adminUsuario();
        if(nuevo_usuario.crearUsuario(this, usuario,cuenta)){
            Toast.makeText(this, "El usuario ha sido creado exitosamente", Toast.LENGTH_SHORT).show();
        }

    }
}