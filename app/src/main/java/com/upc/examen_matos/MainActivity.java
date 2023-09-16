package com.upc.examen_matos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.upc.examen_matos.entidades.Plato;
import com.upc.examen_matos.modelo.PlatoDAO;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar;
    EditText boxNombre, boxCategoria, boxPrecio, boxCantidad, boxObservaciones, boxFecha;
    Plato plato;

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
                PlatoDAO platoDAO = new PlatoDAO(this);
                platoDAO.permisoDB();

                String mensaje = "";

                mensaje = platoDAO.registrarPlato(this.plato);

                mostrarMensaje(mensaje);
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialogInterface, which) -> {
            Intent intent = new Intent(this, ListarActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
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

        if (valida) {
            plato = new Plato(nombre, categoria,observaciones,
                    Double.parseDouble(precio),
                    Integer.parseInt(cantidad),
                    fecha);
        }

        return  valida;
    }
}