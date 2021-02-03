package com.example.masterclass_tema_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registrarse extends AppCompatActivity implements View.OnClickListener {
    EditText us, pas, nom, ap;
    Button reg, can;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);

        //Para ocultar el acttion bar de arriba de la app
        getSupportActionBar().hide();

        us = (EditText) findViewById(R.id.RegUser);
        pas = (EditText) findViewById(R.id.RegPass);
        nom = (EditText) findViewById(R.id.RegNombre);
        ap = (EditText) findViewById(R.id.RegApellido);
        reg = (Button) findViewById(R.id.btnRegRegistrar);
        can = (Button) findViewById(R.id.btnRegCancelar);
        dao = new daoUsuario(this);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegRegistrar:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellido(ap.getText().toString());
                if (!u.isNull()) {
                    Toast.makeText(this, "ERROR: Campos Vacios", Toast.LENGTH_LONG).show();
                } else if (dao.insertUsuario(u)) {
                    Toast.makeText(this, "¡Registro Exitoso!", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Registrarse.this, Inicio.class);
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this, "Usuario ya Registrado", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.btnRegCancelar:
                Intent i = new Intent(Registrarse.this, Inicio.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
