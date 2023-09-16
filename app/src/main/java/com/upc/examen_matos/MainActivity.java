package com.upc.examen_matos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar;
    EditText boxNombre, boxCategoria, boxPrecio, boxCantidad, boxObservaciones, boxFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarReferencias();
        verificarSiVieneDatos();
    }

    private void verificarSiVieneDatos() {

    }

    private void asignarReferencias() {
        btnRegistrar = findViewById(R.id.btnRegistrar);
        boxNombre = findViewById(R.id.boxNombre);
        boxCategoria = findViewById(R.id.boxCategoria);
        boxPrecio = findViewById(R.id.boxPrecio);
        boxCantidad = findViewById(R.id.boxCantidad);
        boxObservaciones = findViewById(R.id.boxObservaciones);
        boxFecha = findViewById(R.id.boxFecha);

        btnRegistrar.setOnClickListener(view -> {
            if (capturarDatos()){
                //TODO Lógica para registrar en BD
            }
        });
    }

    private boolean capturarDatos() {
        boolean valida = true;

        String nombre = boxNombre.getText().toString();
        String categoria = boxCategoria.getText().toString();
        String precio = boxPrecio.getText().toString();
        String cantidad = boxCantidad.getText().toString();
        String observaciones = boxObservaciones.getText().toString();
        String fecha = boxFecha.getText().toString();

        if (nombre.isEmpty()){
            boxNombre.setError("Nombre del plato es obligatorio");
            valida = false;
        }
        if (categoria.isEmpty()){
            boxCategoria.setError("Categoria del plato es obligatorio");
            valida = false;
        }
        if (precio.isEmpty()){
            boxPrecio.setError("Precio del plato es obligatorio");
            valida = false;
        }
        if (cantidad.isEmpty()){
            boxCantidad.setError("Cantidad del plato es obligatorio");
            valida = false;
        }
        if (observaciones.isEmpty()){
            boxObservaciones.setError("Observaciones del plato es obligatorio");
            valida = false;
        }
        if (fecha.isEmpty()){
            boxFecha.setError("Fecha del pedido es obligatorio");
            valida = false;
        }

        return  valida;
    }
}