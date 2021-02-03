package com.example.masterclass_tema_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Inicio extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        //Para ocultar el acttion bar de arriba de la app
        getSupportActionBar().hide();

        user = (EditText) findViewById(R.id.User);
        pass = (EditText) findViewById(R.id.Password);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        dao = new daoUsuario(this);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if (u.equals("") && p.equals("")) {
                    Toast.makeText(this, "ERROR: Campos Vacíos", Toast.LENGTH_LONG).show();
                } else if (dao.login(u, p) == 1) {
                    Usuario ux = dao.getUsuario(u, p);
                    Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Inicio.this, Clase.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                } else {
                    Toast.makeText(this, "Usuario y/o Password Incorrectos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnRegistrar:
                Intent i = new Intent(Inicio.this, Registrarse.class);
                startActivity(i);
                break;
        }
    }

}
