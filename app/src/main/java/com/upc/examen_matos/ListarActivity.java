package com.upc.examen_matos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upc.examen_matos.entidades.Plato;
import com.upc.examen_matos.modelo.PlatoDAO;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    PlatoDAO platoDAO = new PlatoDAO(this);
    List<Plato> platos = new ArrayList<>();
    Adaptador adaptador;
    RecyclerView rvPlatos;
    FloatingActionButton btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        asignarReferencias();
        mostrarPlatos();
    }

    private void asignarReferencias() {
        rvPlatos = findViewById(R.id.rvPlatos);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void mostrarPlatos() {
        platoDAO.permisoDB();
        platos = platoDAO.listarPlatos();
        adaptador = new Adaptador(this, platos);
        rvPlatos.setAdapter(adaptador);
        rvPlatos.setLayoutManager(new LinearLayoutManager(this));
    }
}