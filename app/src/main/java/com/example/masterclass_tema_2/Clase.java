package com.example.masterclass_tema_2;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Clase extends AppCompatActivity {

    Controlador controlador;
    private ArrayAdapter<String> mAdapter;
    ListView listaViewTareas;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clase);
        controlador = new Controlador(this);
        listaViewTareas = (ListView) findViewById(R.id.listaTareas);
        Toast.makeText(this, "Tarea Añadida", Toast.LENGTH_LONG).show();
        actualizarUI();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        final EditText cajaTexto = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("NUEVA TAREA")
                .setMessage("Añade la Tarea que deseas Realizar: ")
                .setView(cajaTexto)
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tarea = cajaTexto.getText().toString();
                        controlador.addTarea(tarea);
                        actualizarUI();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();

        return super.onOptionsItemSelected(item);
    }

    private void actualizarUI() {
        if (controlador.numeroRegistros() == 0) {
            listaViewTareas.setAdapter(null);
        } else {
            mAdapter = new ArrayAdapter<>(this, R.layout.item_tarea, R.id.task_table, controlador.obtenerTareas());
            listaViewTareas.setAdapter(mAdapter);
        }
    }


    public void borrarTarea(View view) {
        View parent = (View) view.getParent();
        TextView tareaTextView = (TextView) parent.findViewById(R.id.task_table);
        String tarea = tareaTextView.getText().toString();
        controlador.borrarTarea(tarea);
        Toast.makeText(this, "Tarea Realizada", Toast.LENGTH_LONG).show();
        actualizarUI();
    }

}
