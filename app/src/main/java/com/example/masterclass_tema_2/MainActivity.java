package com.example.masterclass_tema_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView texto_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para ocultar el acttion bar de arriba de la app
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Inicio.class);
            startActivity(intent);
            finish();
        }, 4000);


        this.texto_inicio = (TextView) findViewById(R.id.texto);

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotate.setFillAfter(true);
        rotate.setRepeatMode(Animation.RESTART);

        this.texto_inicio.startAnimation(rotate);
    }

}