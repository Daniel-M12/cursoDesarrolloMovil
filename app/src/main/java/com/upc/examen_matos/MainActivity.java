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
    int codigo;

    boolean esNuevoRegistro = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarReferencias();
        verificarSiVieneDatos();
    }

    private void verificarSiVieneDatos() {
        if (getIntent().hasExtra("var_id")){
            esNuevoRegistro = false;
            this.codigo = Integer.parseInt(getIntent().getStringExtra("var_id"));
            this.boxNombre.setText(getIntent().getStringExtra("var_nombre"));
            this.boxCategoria.setText(getIntent().getStringExtra("var_categoria"));
            this.boxPrecio.setText(getIntent().getStringExtra("var_precio"));
            this.boxCantidad.setText(getIntent().getStringExtra("var_cantidad"));
            this.boxObservaciones.setText(getIntent().getStringExtra("var_observacion"));
            this.boxFecha.setText(getIntent().getStringExtra("var_fecha"));
        }
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

                if (esNuevoRegistro){
                    mensaje = platoDAO.registrarPlato(this.plato);
                } else {
                    mensaje = platoDAO.editarPlato(this.plato);
                }

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
            if (esNuevoRegistro){
                // Para registro
                plato = new Plato(nombre, categoria,observaciones,
                        Double.parseDouble(precio),
                        Integer.parseInt(cantidad),
                        fecha);
            } else {
                // Para actualización
                plato = new Plato(codigo, nombre, categoria,observaciones,
                        Double.parseDouble(precio),
                        Integer.parseInt(cantidad),
                        fecha);
            }

        }

        return  valida;
    }
}